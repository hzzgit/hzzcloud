package ${packpath};
/**
* ${tableconment}业务层
*/
import ${packageName}.common.vo.${entityName}.*;
import ${packageName}.common.dto.${entityName}.*;
import ${packageName}.entity.${entityName};
import ${packageName}.service.I${entityName}Service;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;


public interface IT${entityName}Service  extends IBaseService<T${entityName}Mapper, T${entityName}, Long> {

PageSerializable<${entityName}<#noparse>TableVO></#noparse> list(${entityName}SearchDTO ${entityName?lower_case}searchDTO, PageParamDTO pageParamDTO);


    void create(Create${entityName}DTO create${entityName?lower_case}DTO);

    void edit(Edit${entityName}DTO ${entityName?lower_case}DTO);

    ${entityName}VO getDetail(String Id);
}