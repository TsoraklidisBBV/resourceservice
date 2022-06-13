//package com.resourceservice.service;
//
//import com.resourceservice.model.ResourceClassDTO;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.http.ResponseEntity;
//
//import java.io.IOException;
//
//@ExtendWith(PactConsumerTestExt.class)
//@PactTestFor(providerName = "ResourceService")
//public class ResourceClassControllerPactTest {
//
//    @Pact(consumer = "ProductCatalogue")
//    public ResponseEntity<ResourceClassDTO> getResourceById(PactDslWithProvider builder) {
//        return builder
//                .given("product with ID 10 exists", "id", 10)
//                .uponReceiving("get product with ID 10")
//                .path("/resourceclass/58ea852e-022b-4288-9d20-d93feab537d6")
//                .willRespondWith()
//                .status(200)
//                .body(
//                        new PactDslJsonBody()
//                                .integerType("id", 1)
//                                .stringType("uuid", "58ea852e-022b-4288-9d20-d93feab537d6")
//                                .stringType("name", "DELL")
//                        )
//                .toPact();
//    }
//
//    @Test
//    @PactTestFor(pactMethod = "getResourceById", port="9999")
//    void testResourceById(MockServer mockServer) throws IOException {
//        Product product = productServiceClient.getProductById(10L);
//        assertThat(product, is(equalTo(new Product(10L, "28 Degrees", "CREDIT_CARD", "v1", "CC_001"))));
//    }
//}
