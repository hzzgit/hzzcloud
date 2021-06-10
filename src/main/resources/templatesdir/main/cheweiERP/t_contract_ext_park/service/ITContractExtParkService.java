package cheweiERP.t_contract_ext_park.service;
/**
* 车场合同附加协议业务层
*/
import com.cwgj.service.data.common.vo.contractextpark.*;
import com.cwgj.service.data.common.dto.contractextpark.*;
import com.cwgj.service.data.entity.TContractExtPark;
import com.cwgj.service.data.service.ITContractExtParkService;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;


public interface ITContractExtParkService  extends IBaseService<TContractExtParkMapper, TContractExtPark, Long> {

    PageSerializable<ContractExtParkTableVO> list(ContractExtParkSearchDTO contractextparksearchDTO, PageParamDTO pageParamDTO);

    void create(CreateContractExtParkDTO createcontractextparkDTO);

    void edit(EditContractExtParkDTO editcontractextparkDTO);

    ContractExtParkVO getDetail(Long Id);
}