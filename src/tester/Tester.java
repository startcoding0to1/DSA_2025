package tester;

import java.util.concurrent.CompletableFuture;

public class Tester {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello world");
            return "\"Hello world\"";
        });
        future.complete("Reeplace");
        System.out.println(future.join());
    }
}