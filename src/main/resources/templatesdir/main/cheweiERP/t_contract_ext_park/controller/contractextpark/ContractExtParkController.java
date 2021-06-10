package cheweiERP.t_contract_ext_park.controller.contractextpark;


import com.cwgj.service.data.common.vo.contractextpark.*;
import com.cwgj.service.data.common.dto.contractextpark.*;
import com.cwgj.service.data.entity.TContractExtPark;
import com.cwgj.service.data.service.ITContractExtParkService;

import com.cwgj.common.model.dto.PageParamDTO;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* 车场合同附加协议接口层
*/
@Api(tags = "车场合同附加协议接口")
@RequestMapping("/contractextpark")
@RestController
@Slf4j
public class ContractExtParkController{

@Autowired
private ITContractExtParkService contractextparkService;


    @PostMapping("/create")
    @ApiOperation("创建车场合同附加协议")
    public void create(@Validated @RequestBody CreateContractExtParkDTO createcontractextparkDTO) {
     contractextparkService.create(createcontractextparkDTO);
    }


    @PostMapping("/edit")
    @ApiOperation("编辑车场合同附加协议")
    public void edit(@Validated @RequestBody EditContractExtParkDTO editcontractextparkDTO) {
      contractextparkService.edit(editcontractextparkDTO);
    }


    @GetMapping("/list")
    @ApiOperation("获取车场合同附加协议列表")
    public PageSerializable<ContractExtParkTableVO> list(ContractExtParkSearchDTO contractextparksearchDTO, PageParamDTO pageParamDTO) {
     return contractextparkService.list(contractextparksearchDTO, pageParamDTO);
    }

    @GetMapping("/{contractextId}")
    @ApiOperation("获取车场合同附加协议详情")
    public ContractExtParkVO getDetail(@PathVariable Long contractextId) {
     return contractextparkService.getDetail(contractextId);
    }

}