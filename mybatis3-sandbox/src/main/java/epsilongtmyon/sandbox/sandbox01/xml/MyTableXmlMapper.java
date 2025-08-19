package epsilongtmyon.sandbox.sandbox01.xml;

import java.math.BigInteger;
import java.util.List;

import epsilongtmyon.common.db.entity.MyTable;

public interface MyTableXmlMapper {

	MyTable findByKey(BigInteger id);

	List<MyTable> findAll();
}
