package ${packpath};


import ${packageName}.common.vo.${sentityName}.*;
import ${packageName}.common.dto.${sentityName}.*;
import ${packageName}.entity.T${entityName};
import ${packageName}.service.IT${entityName}Service;

import com.cwgj.common.model.dto.PageParamDTO;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/edit")
    @ApiOperation("编辑${tableconment}")
    public void edit(@Validated @RequestBody Edit${entityName}DTO edit${entityName?lower_case}DTO) {
      ${entityName?lower_case}Service.edit(edit${entityName?lower_case}DTO);
    }


    @GetMapping("/list")
    @ApiOperation("获取${tableconment}列表")
    public PageSerializable<${entityName}<#noparse>TableVO></#noparse> list(${entityName}SearchDTO ${entityName?lower_case}searchDTO, PageParamDTO pageParamDTO) {
     return ${entityName?lower_case}Service.list(${entityName?lower_case}searchDTO, pageParamDTO);
    }

    @GetMapping("/{${pricolname}}")
    @ApiOperation("获取${tableconment}详情")
    public ${entityName}VO getDetail(@PathVariable Long ${pricolname}) {
     return ${entityName?lower_case}Service.getDetail(${pricolname});
    }

}