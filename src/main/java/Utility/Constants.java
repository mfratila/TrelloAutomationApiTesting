package Utility;

public interface Constants {
     String CONFIG_FILE_PATH = "./Test_Configuration/config.properties";
     String POSTRequest_PAYLOAD_PATH = "Test_Data/PostRequest_Payload";
     String PUTRequest_PAYLOAD_PATH = "Test_Data/PutRequest_Payload";
    String POST_ENDPOINT = "/boards/";
    String GET_ALL_ENDPOINT = "/members/me/boards";
    String GET_BY_ID_ENDPOINT = "/boards/{id}";
    String DELETE_ENDPOINT = "/boards/{id}";
    String PUT_ENDPOINT = "/boards/{id}";
}
