package model.dao.file.fileAccess;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FileAccessTest {

  @Test
  public void fileReadTest() {
	  String testeArquivo = FileAccess.getInstance().fileRead("database/test/testearquivo.txt");
	  Assert.assertEquals(testeArquivo, "testearquivo");
  }
}
