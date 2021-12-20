package com.example.currencychecker.client.dto.response;

import lombok.*;

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

    @lombok.Data
    private static class GeneralData {

        private Image images;

        @lombok.Data
        private static class Image {

            private Original original;

            @lombok.Data
            private static class Original {

                private String url;
            }
        }
    }
}
