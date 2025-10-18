package me.amirkazemzade.myshatelmobilewidget.data.interceptors

import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.GoToMainException
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.InvalidAuthenticationException
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.UnhandledRedirectException
import okhttp3.Interceptor
import okhttp3.Response

class HtmlRedirectInterceptor : Interceptor {

    // Regex to find the href value in <a href="...">
    private val hrefRegex = """href="([^"]*)"""".toRegex()

    // Define a reasonable byte limit to peek. 1KB should be enough
    // for the <title> tag.
    private val peekByteLimit = 1024L
    private val redirectSignature = "<title>Redirecting...</title>"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Only apply logic if:
        // 1. The request *wanted* JSON.
        // 2. The response was 'successful' (e.g., 200 OK).
        // We completely ignore the 'Content-Type' header as it is unreliable.
        if (response.isSuccessful &&
            request.header("Accept")?.contains("application/json") == true
        ) {
            // Peek at the *start* of the response body without consuming it
            val bodyString = response.peekBody(peekByteLimit).string()

            // Check for your specific redirect page signature
            if (bodyString.contains(redirectSignature)) {

                // It's a redirect. Parse the href from the peeked string.
                val href = hrefRegex.find(bodyString)?.groups?.get(1)?.value

                // Throw the appropriate custom exception
                when (href) {
                    "/login/username/" -> throw InvalidAuthenticationException()
                    "/" -> throw GoToMainException()
                    else -> throw UnhandledRedirectException(href)
                }
            }
        }

        // --- Not a Redirect ---
        // If the signature wasn't found, we assume it's the JSON payload
        // (even with the wrong header).
        // We return the original response. Since we used peekBody(),
        // the body is untouched and Retrofit can now parse it as JSON.
        return response
    }
}