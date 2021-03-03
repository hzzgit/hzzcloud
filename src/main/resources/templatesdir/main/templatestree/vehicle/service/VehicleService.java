package templatestree.vehicle.service;
/**
* 车辆表业务层
*/
import com.ltmonitor.entity.Department;
import com.ltmonitor.service.IDepartmentService;
import com.ltmonitor.service.IQueryService;
import com.ltmonitor.vo.PaginateResult;
import com.ltmonitor.web.controller.jt809.vo.ConfigTreeNode;
import com.ltmonitor.web.service.vehiclecache.RealDataServiceRedis;
import com.ltmonitor.web.util.ConverterUtils;
import com.ltmonitor.web.vo.GpsTransferTreeNode;
import com.ltmonitor.web.vo.Uservehicleauthority;
import com.ltmonitor.web.vo.Vehiclebydep;
import lombok.extern.slf4j.Slf4j;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.util.LoopUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class VehicleService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private RealDataServiceRedis realDataServiceRedis;

@Autowired
private IQueryService queryService;

@Autowired
protected IDepartmentService departmentService;

public List<Department> getAuthorizedDepList(List<Long> depIdList) {
        List<Department> depList = null;
            depList = departmentService.fetchDepartmentList(depIdList);//这里是找到机构的具体属性
            Collections.sort(depList, new Comparator<Department>() {

                public int compare(Department o1, Department o2) {
                if (o1.getName() == null)
                return -1;
                return o1.getName().compareTo(o2.getName());
                }
                });
                return depList;
                }


                /**
                * 获取到组织机构树
                *
                * @param id
                * @param depList
                * @return
                */
                public Map depNodeTree(Integer id, List<Department> depList) {
                    Map alldata = new HashMap();
                    Map params = new HashMap();
                    List<Long> depids = new ArrayList<>();
                        String sql = "select * from subiaodb.vehiclebydep where mainid = ?  and deleted = 0";
                        List<Vehiclebydep> datas = jdbcUtil.sql(sql).addIndexParam(id).query(Vehiclebydep.class);
                        Map<Long, Boolean> autoischeckMap = new HashMap<>();
                        if (ConverterUtils.isList(datas)) {
                        for (Vehiclebydep gpstransferbydep : datas) {
                        autoischeckMap.put(gpstransferbydep.getDepid(), true);
                        }
                        }

                        Map<String, GpsTransferTreeNode> deptreemap = new LinkedHashMap<>();
                        for (Department dep : depList) {
                        long depId = dep.getEntityId();
                        long parentId = dep.getParentId();
                        String parentId1 = parentId > 0 ? ("dep" + parentId) : "1";
                        GpsTransferTreeNode depNode = new GpsTransferTreeNode();
                        depNode.setId("dep" + depId);
                        depNode.setDepId(depId);
                        depNode.setPid(parentId1);
                        depNode.setName(dep.getName());
                        if (autoischeckMap.containsKey(depId)) {
                        depids.add(depId);
                        }
                        depNode.setDepId((int) dep.getEntityId());
                        deptreemap.put(depNode.getId(), depNode);
                        }
                        List<String> dellist = new ArrayList<>();

                            deptreemap.forEach((k, v) -> {
                            String pid = v.getPid();
                            GpsTransferTreeNode pnode = deptreemap.get(pid);
                            if (pnode != null) {
                            pnode.getChildren().add(v);
                            dellist.add(v.getId());
                            }
                            });
                            for (String did : dellist) {
                            deptreemap.remove(did);
                            }
                            alldata.put("tree", deptreemap);
                            alldata.put("depIdcheck", depids);
                            return alldata;
                            }

                            /**
                            * 先删除再全部插入，并加入事务,保存配置的机构
                            *
                            * @param deletedepId
                            * @param adddepIds
                            * @param id 车辆表id
                            */
                            @Transactional

                            public void saveByDep(String[] deletedepId, String[] adddepIds, Long id) {
                            try {
                            if (deletedepId != null) {
                            for (String s : deletedepId) {
                            String sql = "update vehiclebydep set deleted = 1 where mainid = ? and depid = ?";
                            jdbcUtil.sql(sql).addIndexParam(id, s).executeUpdate();
                            }
                            }
                            if (adddepIds != null) {
                            for (String depId : adddepIds) {
                            Vehiclebydep vehiclebydep  = new Vehiclebydep ();
                            vehiclebydep.setMainid(id);
                            vehiclebydep.setCreatedate(new Date());
                            vehiclebydep.setDeleted(new Long(0));
                            vehiclebydep.setId(new Long(0));
                            vehiclebydep.setDepid(ConverterUtils.toLong(depId));
                            jdbcUtil.insert( vehiclebydep).execute();
                            }
                            }

                            } catch (Exception e) {
                            log.error("保存车辆表机构授权配置出错", e);
                            throw e;
                            }
                            }



                            /**
                            * 保存配置的车辆授权表表
                            *
                            * @param id    车辆表配置id,vehicleid
                            * @param addvehicleIds    要添加的车辆id数组
                            * @param deletevehicleIds 要删除的车辆id数组
                            */
                            public boolean saveByVehicle(String[] addvehicleIds, String[] deletevehicleIds, Long id) {
                            boolean arg = true;
                            try {
                            if (deletevehicleIds != null && deletevehicleIds.length > 0) {//这边是删除车辆
                            for (String deletevehicleId : deletevehicleIds) {
                            String sql = "update subiaodb.vehiclebyvehicle set deleted = 1 where mainid = ? and vehicleId = ?";
                            jdbcUtil.sql(sql).addIndexParam(id, deletevehicleId).executeUpdate();
                            }
                            }

                            List< Vehiclebyvehicle> vehiclebyvehicles = new ArrayList<>();
                            if (addvehicleIds != null && addvehicleIds.length > 0) {//这边是添加的车辆批量添加
                            for (String vehicleId : addvehicleIds) {
                            Vehiclebyvehicle vehiclebyvehicle=new Vehiclebyvehicle();
                            vehiclebyvehicle.setMainid(id);
                            vehiclebyvehicle.setCreatedate(new Date());
                            vehiclebyvehicle.setDeleted(0L);
                            vehiclebyvehicle.setVehicleid(ConverterUtils.toLong(vehicleId));
                            vehiclebyvehicle.setId(0L);
                            vehiclebyvehicles.add(vehiclebyvehicle);
                            }

                            LoopUtil.splitLoop(vehiclebyvehicles, 1000, list -> {
                            try {
                            jdbcUtil.insertList(list).setLog(log, "批量插入车辆表车辆配置").setNotPrint()
                            .executeBatch(false);
                            } catch (Exception e) {
                            log.debug("插入车辆表车辆配置报错，转为单条插入", e);
                            for (Vehiclebyvehicle vehiclebyvehicle : vehiclebyvehicles) {
                            try {
                            jdbcUtil.insert(vehiclebyvehicle).execute();
                            } catch (Exception e1) {
                            log.error("单条插入车辆表车辆报错", e);
                            }
                            }
                            }
                            });
                            }
                            } catch (Exception e) {
                            log.error("保存车辆表车辆授权配置出错", e);
                            throw e;
                            }
                            return arg;
                            }


                            /**
                            * 获取到有选中车辆的组织机构树
                            *
                            * @param id
                            * @param uservehicleauthority
                            * @return
                            */
                            public Map depandceNodeTree(Integer id, Uservehicleauthority uservehicleauthority) {
                            String sql = "select * from vehiclebyvehicle where mainId=?  and deleted = 0";
                            List<Vehiclebyvehicle> vehiclebyvehicleList = jdbcUtil.sql(sql).addIndexParam(id).query(Vehiclebyvehicle.class);
                            List<Long> vehicleIds = new ArrayList<>();
                                Map<Long, Integer> depcomap = new HashMap<>();
                                List<Long> depIds = new ArrayList<>();
                                    List<Long> depList = uservehicleauthority.getDepIdList();
                                        List<Long> vehicleIdList = uservehicleauthority.getVehicleIdList();
                                            Map<Long, Boolean> depMap = new HashMap<>();
                                            Map<Long, Boolean> vehicleMap = new HashMap<>();
                                            if (ConverterUtils.isList(depList)) {
                                            for (Long aLong : depList) {
                                            depMap.put(aLong, true);
                                            }
                                            }

                                            List<Long> vedepList = new ArrayList<>();
                                                Map<Long, Boolean> vedepMap = new HashMap<>();
                                                if (ConverterUtils.isList(vehicleIdList)) {
                                                for (Long aLong : vehicleIdList) {
                                                vehicleMap.put(aLong, true);
                                                long depId = realDataServiceRedis.getVehiclemap().get(ConverterUtils.toString(aLong)).getDepId();
                                                if (!depMap.containsKey(depId)) {
                                                depMap.put(depId, true);
                                                depList.add(depId);
                                                vedepList.add(depId);
                                                vedepMap.put(depId, true);
                                                }
                                                }

                                                }
                                                List<Department> departmentList = getAuthorizedDepList(depList);

                                                    for (Vehiclebyvehicle vehiclebyvehicle : vehiclebyvehicleList) {
                                                    Long depId = new Long(0);
                                                    if (realDataServiceRedis.getVehiclemap().get(ConverterUtils.toString(vehiclebyvehicle.getVehicleid(), "")) != null) {
                                                    depId = realDataServiceRedis.getVehiclemap().get(ConverterUtils.toString(vehiclebyvehicle.getVehicleid(), "")).getDepId();
                                                    }
                                                    if (depMap.containsKey(depId) || vehicleMap.containsKey(vehiclebyvehicle.getVehicleid())) {
                                                    vehicleIds.add(vehiclebyvehicle.getVehicleid());
                                                    }
                                                    if (depcomap.containsKey(depId)) {
                                                    Integer co = depcomap.get(depId);
                                                    co = co + 1;
                                                    depcomap.put(depId, co);
                                                    } else {
                                                    depcomap.put(depId, 1);
                                                    }
                                                    }
                                                    depcomap.forEach((p, v) -> {
                                                    depIds.add(p);
                                                    });

                                                    Map<Long, List<HashMap>> vehiclesbydep = new HashMap<>();
                                                        for (Map<String, Object> stringObjectMap : realDataServiceRedis.getVehicleresult()) {
                                                        Long dep = ConverterUtils.toLong(stringObjectMap.get("depId"), 0);
                                                        List vehicleList = new ArrayList<>();
                                                        if (depMap.containsKey(dep)) {
                                                        if (vehiclesbydep.containsKey(dep)) {
                                                        vehicleList = vehiclesbydep.get(dep);
                                                        }
                                                        long vehicleId = ConverterUtils.toLong(stringObjectMap.get("vehicleId"));
                                                        boolean arg = false;
                                                        if (vedepMap.containsKey(dep)) {
                                                        if (vehicleMap.containsKey(vehicleId)) {//必须是存在
                                                        arg = true;
                                                        }
                                                        } else {
                                                        arg = true;
                                                        }

                                                        if (arg) {
                                                        Map vehi = new HashMap();
                                                        vehi.put("vehicleId", ConverterUtils.toString(stringObjectMap.get("vehicleId"), ""));
                                                        vehi.put("plateNo", ConverterUtils.toString(stringObjectMap.get("plateNo"), ""));
                                                        vehi.put("depName", ConverterUtils.toString(stringObjectMap.get("depName"), ""));
                                                        vehi.put("depId", dep);
                                                        vehicleList.add(vehi);
                                                        vehiclesbydep.put(dep, vehicleList);
                                                        }
                                                        }

                                                        }
                                                        Map<String, ConfigTreeNode> deptreemap = new LinkedHashMap<>();
                                                        for (Department dep : departmentList) {
                                                        String depNodeId = "dep" + dep.getEntityId();
                                                        long parentId = dep.getParentId();
                                                        String strParentNodeId = "dep" + parentId;
                                                        if (parentId == 0) {
                                                        strParentNodeId = "1";
                                                        }
                                                        ConfigTreeNode depNode = new ConfigTreeNode(depNodeId, dep.getName(), strParentNodeId);
                                                        int checknum = 0;
                                                        if (depcomap.get(dep.getEntityId()) != null) {
                                                        checknum = depcomap.get(dep.getEntityId());
                                                        }
                                                        depNode.setCheckNum(checknum);
                                                        depNode.setTotalNum(realDataServiceRedis.getDepTotalCount(dep.getEntityId()));//总数
                                                        depNode.setDepId((int) dep.getEntityId());
                                                        depNode.setVehicleList(vehiclesbydep.get(dep.getEntityId()));
                                                        deptreemap.put(depNode.getId(), depNode);
                                                        }
                                                        List<String> dellist = new ArrayList<>();

                                                            deptreemap.forEach((k, v) -> {
                                                            String pid = v.getPid();
                                                            ConfigTreeNode pnode = deptreemap.get(pid);
                                                            if (pnode != null) {
                                                            pnode.getChildren().add(v);
                                                            dellist.add(v.getId());
                                                            }
                                                            });
                                                            for (String did : dellist) {
                                                            deptreemap.remove(did);
                                                            }


                                                            deptreemap.forEach((k, v) -> {
                                                            List<ConfigTreeNode> childnodes = v.getChildren();
                                                                childnodes.forEach(p -> {
                                                                clildadd(p);
                                                                });
                                                                final int[] totalnum = {0};
                                                                final int[] onlinenum = {0};
                                                                childnodes.forEach(p -> {
                                                                totalnum[0] = totalnum[0] + p.getTotalNum();
                                                                onlinenum[0] = onlinenum[0] + p.getCheckNum();
                                                                });
                                                                v.setTotalNum(totalnum[0] + v.getTotalNum());
                                                                v.setCheckNum(onlinenum[0] + v.getCheckNum());
                                                                });


                                                                Map dataall = new HashMap();
                                                                dataall.put("tree", deptreemap);
                                                                dataall.put("depIds", depIds);
                                                                Map allvehicledata = new HashMap();
                                                                for (Long vehicleId : vehicleIds) {
                                                                Map data = new HashMap();
                                                                Vehiclebydep vehiclebydep = realDataServiceRedis.getVehiclemap().get(ConverterUtils.toString(vehicleId));
                                                                if (vehiclebydep != null) {
                                                                data.put("depId", vehiclebydep.getDepId());
                                                                data.put("plateNo", vehiclebydep.getPlateNo());
                                                                allvehicledata.put(vehicleId, data);
                                                                }
                                                                }
                                                                dataall.put("vehicleIds", allvehicledata);
                                                                dataall.put("vehiclesbydep", vehiclesbydep);
                                                                return dataall;
                                                                }






                                                                @Transactional

                                                                public void saveConfigByUser(Long id, String[] deleteuserIdsArr, String[] adduserIdsArr) {
                                                                try {
                                                                if (deleteuserIdsArr != null && deleteuserIdsArr.length > 0) {
                                                                for (String deleteuserid : deleteuserIdsArr) {
                                                                String delsql = "delete from vehicleuserlim where mainid=? and userid = ?";
                                                                jdbcUtil.sql(delsql).addIndexParam(id, deleteuserid).executeUpdate();
                                                                }
                                                                }

                                                                if (adduserIdsArr != null && adduserIdsArr.length > 0) {
                                                                List<Vehicleuserlim> vehicleuserlims = new ArrayList<>();
                                                                for (String userid : adduserIdsArr) {
                                                                Vehicleuserlim vehicleuserlim = new Vehicleuserlim();
                                                                vehicleuserlim.setMainid(id);
                                                                vehicleuserlim.setUserid(Long.parseLong(userid));
                                                                vehicleuserlims.add(vehicleuserlim);
                                                                }
                                                                LoopUtil.splitLoop(vehicleuserlims, 3000, list -> {
                                                                try {
                                                                jdbcUtil.insertList(list).setNotPrint().executeBatch(false);
                                                                } catch (Exception e) {
                                                                log.debug("批量用户和配置表绑定错误", e);
                                                                list.forEach(v -> {
                                                                jdbcUtil.insert(v).setNotPrint().execute();
                                                                });
                                                                }
                                                                });
                                                                }
                                                                } catch (Exception e) {
                                                                log.error("车辆表用户授权配置保存出错", e);
                                                                throw e;
                                                                }
                                                                }


                                                                private void clildadd(ConfigTreeNode vehicleTreeNode) {
                                                                List<ConfigTreeNode> vehicleTreeNodes = vehicleTreeNode.getChildren();
                                                                    if (vehicleTreeNodes.size() > 0) {
                                                                    final int[] totalnum = {0};
                                                                    final int[] onlinenum = {0};
                                                                    vehicleTreeNodes.forEach(p -> {
                                                                    clildadd(p);
                                                                    totalnum[0] = totalnum[0] + p.getTotalNum();
                                                                    onlinenum[0] = onlinenum[0] + p.getCheckNum();
                                                                    });
                                                                    vehicleTreeNode.setTotalNum(totalnum[0] + vehicleTreeNode.getTotalNum());
                                                                    vehicleTreeNode.setCheckNum(onlinenum[0] + vehicleTreeNode.getCheckNum());
                                                                    }
                                                                    //        vehicleTreeNode.setName(vehicleTreeNode.getName() + "(" + vehicleTreeNode.getCheckNum()
                                                                    //                + "/" + vehicleTreeNode.getTotalNum() + ")");
                                                                    }





                                                                    /**
                                                                    * 分页查询车辆表
                                                                    */
                                                                    public PaginateResult selectlist(Map param, int page, int pagesize) {
                                                                    String queryId = "vehiclemapper.selectlist";
                                                                    PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
                                                                    return paginateResult;
                                                                    }


                                                                    /**
                                                                    * 保存车辆表
                                                                    */
                                                                    public void save(Vehicle vehicle,Long userid) throws Exception {
                                                                    if (vehicle.getVehicleid() == 0) {//新增
                                                                    vehicle.setCreatedate(new Date());
                                                                    vehicle.setUserid(userid);
                                                                    jdbcUtil.insert(vehicle).insertColumn(ColumnSet.all()).execute();
                                                                    } else {//修改
                                                                    vehicle.setUpdatedate(new Date());
                                                                    jdbcUtil.update(vehicle).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Vehicle.F_createdate,
                                                                    Vehicle.F_deleted,Vehicle.F_id,Vehicle.F_userid)).execute();
                                                                    }
                                                                    }




                                                                    /**
                                                                    * 删除车辆表,更改标志位,假删除
                                                                    */
                                                                    public void fdelete(String Id) throws Exception {

                                                                    String sql = "update subiaodb.vehicle set deleted =id where vehicleid=? ";
                                                                    jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
                                                                    }

                                                                    /**
                                                                    * 删除车辆表,真删除
                                                                    */
                                                                    public void delete(String Id) throws Exception {
                                                                    String sql = "delete from  subiaodb.vehicle  where vehicleid=? ";
                                                                    jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
                                                                    }

                                                                    }