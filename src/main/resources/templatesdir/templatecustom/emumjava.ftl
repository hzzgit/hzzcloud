package ${packpath};

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

public enum emumjava  {

<#list coluvos as coluvo>
    code${coluvo_index} ("${coluvo.code}","${coluvo.message}","${coluvo.result}" )<#if coluvo_index == coluvos?size-1> ;<#else>,</#if>

    </#list>

private String code;

private String message;
private String result;


emumjava(String code, String message, String result) {
this.code = code;
this.message = message;
this.result = result;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getResult() {
return result;
}

public void setResult(String result) {
this.result = result;
}


}