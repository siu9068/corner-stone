package kr.cornerstone.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResponseCustom {

    private int statusCode;
    private Object result;

    public static ResponseCustom ok(int statusCode, Object result){

        return new ResponseCustom(statusCode, result);
    }

//    public static ResponseCustom error(int statusCode){
//        return new ResponseCustom(statusCode, null);
//    }

    public static ResponseCustom error(int statusCode, Object result){
        return new ResponseCustom(statusCode, result);
    }

    public static ResponseEntity of(HttpStatus httpStatus,Object result){

        return ResponseEntity.status(httpStatus).body(ResponseCustom.ok(httpStatus.value(),result));

    }

//    public static ResponseEntity ofError(HttpStatus httpStatus){
//
//        return ResponseEntity.status(httpStatus).body(ResponseCustom.error(httpStatus.value()));
//    }

    public static ResponseEntity ofError(HttpStatus httpStatus,Object result){


        return ResponseEntity.status(httpStatus).body(ResponseCustom.error(httpStatus.value(),result));
    }






}


