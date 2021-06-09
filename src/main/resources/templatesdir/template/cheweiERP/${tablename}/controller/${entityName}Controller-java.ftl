package ${packpath};

import com.cwgj.common.model.dto.PageParamDTO;
import com.cwgj.common.validate.Get;
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
/**
* ${tableconment}接口层
*/
@Api(tags = "${tableconment}接口")
@RequestMapping("/${entityName?lower_case}")
@RestController
@Slf4j
public class ${entityName}Controller{

@Autowired
private IT${entityName}Service ${entityName?lower_case}Service;


@PostMapping("/create")
@ApiOperation("创建${tableconment}")
public void create(@Validated @RequestBody Create${entityName}DTO create${entityName?lower_case}DTO) {
${entityName?lower_case}Service.create(create${entityName?lower_case}DTO);
}

@GetMapping("/list")
@ApiOperation("获取${tableconment}列表")
public PageSerializable<${entityName}<#noparse>VO></#noparse> list(${entityName}SearchDTO ${entityName?lower_case}searchDTO, PageParamDTO pageParamDTO) {
    return ${entityName?lower_case}Service.list(${entityName?lower_case}searchDTO, pageParamDTO);
    }




}