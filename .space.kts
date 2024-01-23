job("Publish") {
    container(displayName = "Run Gradle publish task", image = "amazoncorretto:17-alpine") {
        kotlinScript { api ->
            api.gradlew("build")
            api.gradlew("publish")
        }
    }
}
