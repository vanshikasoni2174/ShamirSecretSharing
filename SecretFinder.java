import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

public class SecretFinder {

    static class Share {
        int x;
        BigInteger y;

        Share(int x, BigInteger y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<Share> parseJSON(String fileName) {
        List<Share> shares = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(fileName));

            for (Iterator<String> it = root.fieldNames(); it.hasNext(); ) {
                String key = it.next();
                if (key.equals("keys")) continue;

                JsonNode node = root.get(key);
                System.out.println("🔍 Reading key: " + key + ", node: " + node);

                JsonNode baseNode = node.get("base");
                JsonNode valueNode = node.get("value");

                if (baseNode == null || valueNode == null) {
                    System.err.println("⚠️ Skipping invalid entry: " + key + " => " + node);
                    continue;
                }

                int base = Integer.parseInt(baseNode.asText());
                String value = valueNode.asText();

                shares.add(new Share(Integer.parseInt(key), new BigInteger(value, base)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shares;
    }

    public static BigInteger lagrangeInterpolation(List<Share> shares) {
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < shares.size(); i++) {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int j = 0; j < shares.size(); j++) {
                if (i == j) continue;

                numerator = numerator.multiply(BigInteger.valueOf(-shares.get(j).x));
                denominator = denominator.multiply(BigInteger.valueOf(shares.get(i).x - shares.get(j).x));
            }

            BigInteger term = shares.get(i).y.multiply(numerator).divide(denominator);
            result = result.add(term);
        }

        return result;
    }

    public static void main(String[] args) {
        String fileName = "testcase2.json";  // Ensure this file is in the working directory

        List<Share> shares = parseJSON(fileName);

        if (shares.size() < 2) {
            System.err.println("Not enough valid shares to interpolate.");
            return;
        }

        BigInteger secret = lagrangeInterpolation(shares);
        System.out.println("✅ Reconstructed secret: " + secret);
    }
}
