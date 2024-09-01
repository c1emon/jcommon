package icu.clemon.jcommon.route;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Data
public class Route {
  /** 类名 */
  private String className = "";

  /** 方法名 */
  private String methodName = "";

  /** 匹配规则 */
  private Set<String> patterns = new HashSet<>();

  /** 请求方式 */
  private Set<RequestMethod> methods = new HashSet<>();

  public String getTraefikRule() {

    var pathRules = new LinkedList<String>();
    var methodRules = new LinkedList<String>();
    this.patterns.forEach(
        pattern -> {
          pathRules.add(String.format("`%s`", pattern));
        });

    this.methods.forEach(
        method -> {
          methodRules.add(String.format("`%s`", method.name()));
        });

    var builder =
        new StringBuilder(
            String.format("traefik.http.routers.%s_%s.rule=", this.className, this.methodName));
    if (!pathRules.isEmpty()) {
      builder.append(String.format("Path(%s)", String.join(",", pathRules)));
    }

    if (!methodRules.isEmpty()) {
      builder.append(String.format(" && Method(%s)", String.join(",", methodRules)));
    }

    return builder.toString();
  }
}
