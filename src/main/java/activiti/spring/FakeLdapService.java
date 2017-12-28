package activiti.spring;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeLdapService {

  public String findManagerForEmployee(String employee) {
    System.out.println("表单是");
    return "Kermit The Frog";
  }

  public List<String> findAllSales() {
    return Arrays.asList("kermit", "gonzo", "fozzie");
  }

}