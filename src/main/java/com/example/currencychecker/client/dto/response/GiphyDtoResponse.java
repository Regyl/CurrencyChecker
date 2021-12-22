package com.example.currencychecker.client.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GiphyDtoResponse {
    private ArrayList<GeneralData> data;

    public String getFirstOriginalUrl() {
        return data.get(0)
                .getImages()
                .getOriginal()
                .getUrl();
    }

    @Data
    public static class GeneralData {

        private Image images;

        @Data
        public static class Image {

            private Original original;

            @Data
            public static class Original {

                private String url;
            }
        }
    }
}
