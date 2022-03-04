//package vn.dnict.microservice.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import vn.dnict.microservice.security.model.UserObject;
//
//@FeignClient(name = "uaa-service")//, url="http://103.101.76.123:8000/auth")
//public interface UaaServiceAPI{
//	 @RequestMapping(method = RequestMethod.GET, value = "/uaa/user")
//	 public UserObject fetchUser(@RequestHeader("X-Token") String authorizationToken);
//}
