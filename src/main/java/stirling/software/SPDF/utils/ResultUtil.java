package stirling.software.SPDF.utils;

import java.io.IOException;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import stirling.software.SPDF.domain.Result;

/**
 * @author：xp
 * @date：2024/6/16 13:21
 */
public class ResultUtil {

    /**
     * 设置错误的响应信息
     *
     * @param code 错误码
     * @param msg 错误信息
     * @param response
     * @throws IOException
     */
    public static void setErrorResponse(Integer code, String msg, HttpServletResponse response)
            throws IOException {
        Result error = Result.error(code);
        error.put("msg", msg);
        String s = new ObjectMapper().writeValueAsString(error);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(s);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
