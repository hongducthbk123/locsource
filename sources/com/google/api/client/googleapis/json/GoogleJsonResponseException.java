package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseException.Builder;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import java.io.IOException;

public class GoogleJsonResponseException extends HttpResponseException {
    private static final long serialVersionUID = 409811126989994864L;
    private final transient GoogleJsonError details;

    public GoogleJsonResponseException(Builder builder, GoogleJsonError details2) {
        super(builder);
        this.details = details2;
    }

    public final GoogleJsonError getDetails() {
        return this.details;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.api.client.googleapis.json.GoogleJsonResponseException from(com.google.api.client.json.JsonFactory r11, com.google.api.client.http.HttpResponse r12) {
        /*
            com.google.api.client.http.HttpResponseException$Builder r1 = new com.google.api.client.http.HttpResponseException$Builder
            int r8 = r12.getStatusCode()
            java.lang.String r9 = r12.getStatusMessage()
            com.google.api.client.http.HttpHeaders r10 = r12.getHeaders()
            r1.<init>(r8, r9, r10)
            com.google.api.client.util.Preconditions.checkNotNull(r11)
            r4 = 0
            r3 = 0
            boolean r8 = r12.isSuccessStatusCode()     // Catch:{ IOException -> 0x008c }
            if (r8 != 0) goto L_0x00ae
            java.lang.String r8 = "application/json; charset=UTF-8"
            java.lang.String r9 = r12.getContentType()     // Catch:{ IOException -> 0x008c }
            boolean r8 = com.google.api.client.http.HttpMediaType.equalsIgnoreParameters(r8, r9)     // Catch:{ IOException -> 0x008c }
            if (r8 == 0) goto L_0x00ae
            java.io.InputStream r8 = r12.getContent()     // Catch:{ IOException -> 0x008c }
            if (r8 == 0) goto L_0x00ae
            r7 = 0
            java.io.InputStream r8 = r12.getContent()     // Catch:{ IOException -> 0x0091 }
            com.google.api.client.json.JsonParser r7 = r11.createJsonParser(r8)     // Catch:{ IOException -> 0x0091 }
            com.google.api.client.json.JsonToken r2 = r7.getCurrentToken()     // Catch:{ IOException -> 0x0091 }
            if (r2 != 0) goto L_0x0041
            com.google.api.client.json.JsonToken r2 = r7.nextToken()     // Catch:{ IOException -> 0x0091 }
        L_0x0041:
            if (r2 == 0) goto L_0x005e
            java.lang.String r8 = "error"
            r7.skipToKey(r8)     // Catch:{ IOException -> 0x0091 }
            com.google.api.client.json.JsonToken r8 = r7.getCurrentToken()     // Catch:{ IOException -> 0x0091 }
            com.google.api.client.json.JsonToken r9 = com.google.api.client.json.JsonToken.END_OBJECT     // Catch:{ IOException -> 0x0091 }
            if (r8 == r9) goto L_0x005e
            java.lang.Class<com.google.api.client.googleapis.json.GoogleJsonError> r8 = com.google.api.client.googleapis.json.GoogleJsonError.class
            java.lang.Object r8 = r7.parseAndClose(r8)     // Catch:{ IOException -> 0x0091 }
            r0 = r8
            com.google.api.client.googleapis.json.GoogleJsonError r0 = (com.google.api.client.googleapis.json.GoogleJsonError) r0     // Catch:{ IOException -> 0x0091 }
            r4 = r0
            java.lang.String r3 = r4.toPrettyString()     // Catch:{ IOException -> 0x0091 }
        L_0x005e:
            if (r7 != 0) goto L_0x0086
            r12.ignore()     // Catch:{ IOException -> 0x008c }
        L_0x0063:
            java.lang.StringBuilder r6 = com.google.api.client.http.HttpResponseException.computeMessageBuffer(r12)
            boolean r8 = com.google.api.client.util.Strings.isNullOrEmpty(r3)
            if (r8 != 0) goto L_0x0079
            java.lang.String r8 = com.google.api.client.util.StringUtils.LINE_SEPARATOR
            java.lang.StringBuilder r8 = r6.append(r8)
            r8.append(r3)
            r1.setContent(r3)
        L_0x0079:
            java.lang.String r8 = r6.toString()
            r1.setMessage(r8)
            com.google.api.client.googleapis.json.GoogleJsonResponseException r8 = new com.google.api.client.googleapis.json.GoogleJsonResponseException
            r8.<init>(r1, r4)
            return r8
        L_0x0086:
            if (r4 != 0) goto L_0x0063
            r7.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0063
        L_0x008c:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x0063
        L_0x0091:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x00a1 }
            if (r7 != 0) goto L_0x009b
            r12.ignore()     // Catch:{ IOException -> 0x008c }
            goto L_0x0063
        L_0x009b:
            if (r4 != 0) goto L_0x0063
            r7.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0063
        L_0x00a1:
            r8 = move-exception
            if (r7 != 0) goto L_0x00a8
            r12.ignore()     // Catch:{ IOException -> 0x008c }
        L_0x00a7:
            throw r8     // Catch:{ IOException -> 0x008c }
        L_0x00a8:
            if (r4 != 0) goto L_0x00a7
            r7.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x00a7
        L_0x00ae:
            java.lang.String r3 = r12.parseAsString()     // Catch:{ IOException -> 0x008c }
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.googleapis.json.GoogleJsonResponseException.from(com.google.api.client.json.JsonFactory, com.google.api.client.http.HttpResponse):com.google.api.client.googleapis.json.GoogleJsonResponseException");
    }

    public static HttpResponse execute(JsonFactory jsonFactory, HttpRequest request) throws GoogleJsonResponseException, IOException {
        Preconditions.checkNotNull(jsonFactory);
        boolean originalThrowExceptionOnExecuteError = request.getThrowExceptionOnExecuteError();
        if (originalThrowExceptionOnExecuteError) {
            request.setThrowExceptionOnExecuteError(false);
        }
        HttpResponse response = request.execute();
        request.setThrowExceptionOnExecuteError(originalThrowExceptionOnExecuteError);
        if (!originalThrowExceptionOnExecuteError || response.isSuccessStatusCode()) {
            return response;
        }
        throw from(jsonFactory, response);
    }
}
