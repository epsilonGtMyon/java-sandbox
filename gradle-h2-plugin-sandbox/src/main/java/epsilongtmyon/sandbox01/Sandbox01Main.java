package epsilongtmyon.sandbox01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;

// 起動・停止コマンドの実験
public class Sandbox01Main {

	public static void main(String[] args) throws Exception {
		var main = new Sandbox01Main();
		main.start1();
	}

	private void start1() throws Exception {

		String tcpPort = "9092";
		String webPort = "8082";
		// tcpPasswordがないとシャットダウンできない
		String tcpPassword = "admin";

		// run
		Path p = Path.of("./", "h2").toAbsolutePath();

		List<String> command = new ArrayList<>();
		command.add("-tcp");
		command.add("-web");

		command.add("-tcpPort");
		command.add(tcpPort);

		command.add("-webPort");
		command.add(webPort);

		command.add("-baseDir");
		command.add(p.toString());
		command.add("-ifNotExists");

		command.add("-tcpPassword");
		command.add(tcpPassword);

		Files.createDirectories(p);
		Server.main(command.stream().toArray(String[]::new));

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost:" + tcpPort + "/dbtest", "sa", "")) {
			try (Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select 1")) {

				while (rs.next()) {
					System.out.println(rs.getInt(1));
				}

			}
		}

		System.out.println("run...");
		System.in.read();

		Server.main("-tcpShutdown", "tcp://localhost:" + tcpPort, "-tcpPassword", tcpPassword);

	}
}
