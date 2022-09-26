import org.springframework.web.client.RestTemplate;

public class TestIA {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method and default Headers.
		String result = restTemplate.getForObject(
				"https://www.chessdb.cn/cdb.php?action=querybest&board=r1bqkb1r/ppp2ppp/5n2/n2Pp1N1/2B5/8/PPPP1PPP/RNBQK2R w KQkq%20-%201%206",
				String.class);

		System.out.println(result);

	}
}
