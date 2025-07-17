package com.example.app1.web.response.common;

import lombok.Getter;

@Getter
public class RestResponse<T> {

  private final int statusCode;
  private final String message;
  private final T data;

  private RestResponse(int statusCode, String message, T data) {
    this.statusCode = statusCode;
    this.message = message;
    this.data = data;
  }

  /**
   * 성공 응답을 반환한다. (HTTP 200 OK)
   * @param data 응답할 데이터 객체
   * @return  RestResponse 객체
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> success(T data) {
    return new RestResponse<T>(200, "성공", data);
  }

  /**
   * 메세지와 함께 성공 응답을 반환한다. (HTTP 200 OK)
   * @param message 응답 메세지
   * @param data 응답할 데이터 객체
   * @return RestResponse 객체
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> success(String message, T data) {
    return new RestResponse<T>(200, message, data);
  }

  /**
   * 데이터 없이 성공 응답을 반환한다. (HTTP 200 OK)
   * @return RestResponse 객체
   */
  public static <T> RestResponse<T> success() {
    return new RestResponse<T>(200, "성공", null);
  }

  /**
   * 생성 성공 응답을 반환한다. (HTTP 201 Created)
   * @param data 응답할 데이터 객체
   * @return  RestResponse 객체
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> created(T data) {
    return new RestResponse<T>(201, "생성됨", data);
  }

  /**
   * 메세지와 함께 생성 성공 응답을 반환한다. (HTTP 201 Created)
   * @param message 응답 메세지
   * @param data 응답할 데이터 객체
   * @return  RestResponse 객체
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> created(String message, T data) {
    return new RestResponse<T>(201, message, data);
  }

  /**
   * 클라이언트 요청 오류 응답을 반환한다. (HTTP 400 Bad Request)
   * @param message 오류 메세지
   * @return RestResponse 객체 (데이터는 null)
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> badRequest(String message) {
    return new RestResponse<T>(400, message, null);
  }

  /**
   * 인증 실패 응답을 반환한다. (HTTP 401 Unauthorized)
   * @param message 오류 메세지
   * @return RestResponse 객체 (데이터는 null)
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> unauthorized(String message) {
    return new RestResponse<T>(401, message, null);
  }

  /**
   * 접근 권한 없음 응답을 반환한다. (HTTP 403 Forbidden)
   * @param message 오류 메세지
   * @return RestResponse 객체 (데이터는 null)
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> forbidden(String message) {
    return new RestResponse<T>(403, message, null);
  }

  /**
   * 리소스를 찾을 수 없음 응답을 반환한다. (HTTP 404 Not Found)
   * @param message 오류 메세지
   * @return RestResponse 객체 (데이터는 null)
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> notFound(String message) {
    return new RestResponse<T>(404, message, null);
  }  
  
  /**
   * 요청 메소드가 허용되지 않음 응답을 반환한다. (HTTP 405 Method Not Allowed)
   * @param message 오류 메세지
   * @return RestResponse 객체 (데이터는 null)
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> methodNotAllowed(String message) {
    return new RestResponse<T>(405, message, null);
  }

  /**
   * 내부 서버 오류 응답을 반환한다. (HTTP 500 Internal Server Error)
   * @param message 오류 메세지
   * @return RestResponse 객체 (데이터는 null)
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> internalServerError(String message) {
    return new RestResponse<T>(500, message, null);
  }

  /**
   * 지정된 상태 코드와 메세지로 일반적인 오류 응답을 반환한다.
   * @param statusCode HTTP 상태 코드
   * @param message 오류 메세지
   * @return RestResponse 객체 (데이터는 null)
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> error(int statusCode, String message) {
    return new RestResponse<T>(statusCode, message, null);
  }

  /**
   * 지정된 상태 코드와 메세지, 데이터로 일반적인 응답을 반환한다.
   * @param statusCode HTTP 상태코드
   * @param message 응답 메세지
   * @param data 응답할 데이터 객체
   * @return RestResponse 객체
   * @param <T> 데이터 타입
   */
  public static <T> RestResponse<T> custom(int statusCode, String message, T data) {
    return new RestResponse<T>(statusCode, message, data);
  }
}
