package practice.interview.coupang;

/**
 * Seller Catalog Deduplication
 *
 * You are given product listing feeds from multiple sellers:
 *
 * [seller_id, product_name, brand, category, price]
 *
 * Different sellers may upload the same product using slightly different names.
 *
 * Group listings that likely refer to the same product.
 *
 * Return clusters of duplicate products.
 *
 * Possible follow-ups
 * 	•	Normalize casing and punctuation
 * 	•	Use exact brand match plus fuzzy name match
 * 	•	Detect conflicting prices within the same cluster
 */
public class SellerCatalogDeduplication {
    record product(Integer sellerId, String name, String brand, String category, Double price) {};

    public Boolean isSimilar(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();

        if(Math.abs((char1.length - char2.length)) > (double) char1.length * 0.2) {
            return false;
        }
        int sameCount = 0;

        for(int i = 0 ; i < char1.length && i < char2.length; i++) {
            if(char1[i] == char2[i]) {
                sameCount++;
            }
        }
        if(sameCount < (double) char1.length * 0.8 ) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SellerCatalogDeduplication deduplication = new SellerCatalogDeduplication();
        System.out.println(deduplication.isSimilar(String.valueOf("1234567890"), String.valueOf("2147567890")));
    }

}
