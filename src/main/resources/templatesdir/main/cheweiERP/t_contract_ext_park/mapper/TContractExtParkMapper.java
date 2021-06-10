package cheweiERP.t_contract_ext_park.mapper;
/**
* 车场合同附加协议业务层
*/
import com.cwgj.service.data.common.vo.contractextpark.*;
import com.cwgj.service.data.common.dto.contractextpark.*;
import com.cwgj.service.data.entity.TContractExtPark;
import java.util.List;


public interface TContractExtParkMapper extends Mapper<TContractExtPark, Long> {

List<ContractExtParkTableVO> list(ContractExtParkSearchDTO contractextparksearchDTO);


}