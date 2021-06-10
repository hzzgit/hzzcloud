package cheweiERP.t_contract_ext_park.service.impl;
/**
* 车场合同附加协议业务层
*/
import com.cwgj.service.data.common.vo.contractextpark.*;
import com.cwgj.service.data.common.dto.contractextpark.*;
import com.cwgj.service.data.entity.TContractExtPark;
import com.cwgj.service.data.service.ITContractExtParkService;
import com.cwgj.service.data.core.context.UserDetailDTO;
import com.cwgj.service.data.entity.TContractExtPark;
import com.cwgj.service.data.mapper.TContractExtParkMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
public class TContractExtParkServiceImpl extends AbstractServiceImpl<TContractExtParkMapper, TContractExtPark, Long> implements ITContractExtParkService {

    @Override
    public PageSerializable<ContractExtParkTableVO> list(ContractExtParkSearchDTO contractextparksearchDTO, PageParamDTO pageParamDTO){
    return PageHelper.startPage(pageParamDTO).doSelectPageSerializable(() -> mapper.list(contractextparksearchDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateContractExtParkDTO createcontractextparkDTO){
    final UserDetailDTO userDetailDTO = UserDetailContext.get();
    final Date now = new Date();
    final TContractExtPark contractextpark = BeanCopierUtil.copy(createcontractextparkDTO, TContractExtPark.class)
    .toBuilder()
    .createId(userDetailDTO.getUserId())
    .createName(userDetailDTO.getNickname())
    .handlerId(userDetailDTO.getUserId())
    .handlerName(userDetailDTO.getNickname())
    .createTime(now)
    .updateTime(now)
    .build();
    this.insertSelective(contractextpark);
    }

    @Override
    public void edit(EditContractExtParkDTO editcontractextparkDTO){
    final UserDetailDTO userDetailDTO = UserDetailContext.get();
    final Date now = new Date();
    final TContractExtPark contractextpark = BeanCopierUtil.copy(editcontractextparkDTO, TContractExtPark.class)
    .toBuilder()
    .handlerId(userDetailDTO.getUserId())
    .handlerName(userDetailDTO.getNickname())
    .updateTime(now)
    .build();
    this.updateByPrimaryKeySelective(contractextpark);

    }

    @Override
    public ContractExtParkVO getDetail(Long Id){
    final TContractExtPark contractextpark = this.selectOne(TContractExtPark.builder().contractextId(Id).build());
    BusinessExceptionAssert.notNull(contractextpark, "车场合同附加协议不存在");
    final ContractExtParkVO contractextparkVO = BeanCopierUtil.copy(contractextpark, ContractExtParkVO.class);
    return contractextparkVO;
    }

}