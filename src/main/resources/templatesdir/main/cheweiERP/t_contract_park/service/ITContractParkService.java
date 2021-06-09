package cheweiERP.t_contract_park.service;
/**
* 车场合同业务层
*/
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


public interface ITContractParkService  extends IBaseService<TContractParkMapper, TContractPark, Long> {

PageSerializable<ContractParkVO> list(ContractParkSearchDTO contractparksearchDTO, PageParamDTO pageParamDTO);

}