package ${packpath};
/**
* ${tableconment}业务层
*/
import ${packageName}.common.vo.${sentityName}.*;
import ${packageName}.common.dto.${sentityName}.*;
import ${packageName}.entity.T${entityName};
import java.util.List;


public interface T${entityName}Mapper extends Mapper<T${entityName}, Long> {

List<${entityName}TableVO> list(${entityName}SearchDTO ${entityName?lower_case}searchDTO);


}