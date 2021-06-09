package cheweiERP.t_contract_park.controller;

import com.cwgj.common.model.dto.PageParamDTO;
import com.cwgj.common.validate.Get;
import com.cwgj.service.data.common.vo.ContractPark.*;
import com.cwgj.service.data.common.dto.ContractPark.*;
import com.cwgj.service.data.entity.ContractPark;
import com.cwgj.service.data.service.IContractParkService;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
/**
* 车场合同接口层
*/
@Api(tags = "车场合同接口")
@RequestMapping("/contractpark")
@RestController
@Slf4j
public class ContractParkController{

@Autowired
private ITContractParkService contractparkService;


@PostMapping("/create")
@ApiOperation("创建车场合同")
public void create(@Validated @RequestBody CreateContractParkDTO createcontractparkDTO) {
contractparkService.create(createcontractparkDTO);
}

@GetMapping("/list")
@ApiOperation("获取车场合同列表")
public PageSerializable<ContractParkVO> list(ContractParkSearchDTO contractparksearchDTO, PageParamDTO pageParamDTO) {
    return contractparkService.list(contractparksearchDTO, pageParamDTO);
    }




}