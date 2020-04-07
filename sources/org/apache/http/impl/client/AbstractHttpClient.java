package org.apache.http.impl.client;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;

@ThreadSafe
public abstract class AbstractHttpClient implements HttpClient {
    @GuardedBy("this")
    private ClientConnectionManager connManager;
    @GuardedBy("this")
    private CookieStore cookieStore;
    @GuardedBy("this")
    private CredentialsProvider credsProvider;
    @GuardedBy("this")
    private HttpParams defaultParams;
    @GuardedBy("this")
    private BasicHttpProcessor httpProcessor;
    @GuardedBy("this")
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    private final Log log = LogFactory.getLog(getClass());
    @GuardedBy("this")
    private AuthenticationHandler proxyAuthHandler;
    @GuardedBy("this")
    private RedirectHandler redirectHandler;
    @GuardedBy("this")
    private HttpRequestExecutor requestExec;
    @GuardedBy("this")
    private HttpRequestRetryHandler retryHandler;
    @GuardedBy("this")
    private ConnectionReuseStrategy reuseStrategy;
    @GuardedBy("this")
    private HttpRoutePlanner routePlanner;
    @GuardedBy("this")
    private AuthSchemeRegistry supportedAuthSchemes;
    @GuardedBy("this")
    private CookieSpecRegistry supportedCookieSpecs;
    @GuardedBy("this")
    private AuthenticationHandler targetAuthHandler;
    @GuardedBy("this")
    private UserTokenHandler userTokenHandler;

    /* access modifiers changed from: protected */
    public abstract AuthSchemeRegistry createAuthSchemeRegistry();

    /* access modifiers changed from: protected */
    public abstract ClientConnectionManager createClientConnectionManager();

    /* access modifiers changed from: protected */
    public abstract ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy();

    /* access modifiers changed from: protected */
    public abstract ConnectionReuseStrategy createConnectionReuseStrategy();

    /* access modifiers changed from: protected */
    public abstract CookieSpecRegistry createCookieSpecRegistry();

    /* access modifiers changed from: protected */
    public abstract CookieStore createCookieStore();

    /* access modifiers changed from: protected */
    public abstract CredentialsProvider createCredentialsProvider();

    /* access modifiers changed from: protected */
    public abstract HttpContext createHttpContext();

    /* access modifiers changed from: protected */
    public abstract HttpParams createHttpParams();

    /* access modifiers changed from: protected */
    public abstract BasicHttpProcessor createHttpProcessor();

    /* access modifiers changed from: protected */
    public abstract HttpRequestRetryHandler createHttpRequestRetryHandler();

    /* access modifiers changed from: protected */
    public abstract HttpRoutePlanner createHttpRoutePlanner();

    /* access modifiers changed from: protected */
    public abstract AuthenticationHandler createProxyAuthenticationHandler();

    /* access modifiers changed from: protected */
    public abstract RedirectHandler createRedirectHandler();

    /* access modifiers changed from: protected */
    public abstract HttpRequestExecutor createRequestExecutor();

    /* access modifiers changed from: protected */
    public abstract AuthenticationHandler createTargetAuthenticationHandler();

    /* access modifiers changed from: protected */
    public abstract UserTokenHandler createUserTokenHandler();

    protected AbstractHttpClient(ClientConnectionManager conman, HttpParams params) {
        this.defaultParams = params;
        this.connManager = conman;
    }

    public final synchronized HttpParams getParams() {
        if (this.defaultParams == null) {
            this.defaultParams = createHttpParams();
        }
        return this.defaultParams;
    }

    public synchronized void setParams(HttpParams params) {
        this.defaultParams = params;
    }

    public final synchronized ClientConnectionManager getConnectionManager() {
        if (this.connManager == null) {
            this.connManager = createClientConnectionManager();
        }
        return this.connManager;
    }

    public final synchronized HttpRequestExecutor getRequestExecutor() {
        if (this.requestExec == null) {
            this.requestExec = createRequestExecutor();
        }
        return this.requestExec;
    }

    public final synchronized AuthSchemeRegistry getAuthSchemes() {
        if (this.supportedAuthSchemes == null) {
            this.supportedAuthSchemes = createAuthSchemeRegistry();
        }
        return this.supportedAuthSchemes;
    }

    public synchronized void setAuthSchemes(AuthSchemeRegistry authSchemeRegistry) {
        this.supportedAuthSchemes = authSchemeRegistry;
    }

    public final synchronized CookieSpecRegistry getCookieSpecs() {
        if (this.supportedCookieSpecs == null) {
            this.supportedCookieSpecs = createCookieSpecRegistry();
        }
        return this.supportedCookieSpecs;
    }

    public synchronized void setCookieSpecs(CookieSpecRegistry cookieSpecRegistry) {
        this.supportedCookieSpecs = cookieSpecRegistry;
    }

    public final synchronized ConnectionReuseStrategy getConnectionReuseStrategy() {
        if (this.reuseStrategy == null) {
            this.reuseStrategy = createConnectionReuseStrategy();
        }
        return this.reuseStrategy;
    }

    public synchronized void setReuseStrategy(ConnectionReuseStrategy reuseStrategy2) {
        this.reuseStrategy = reuseStrategy2;
    }

    public final synchronized ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        if (this.keepAliveStrategy == null) {
            this.keepAliveStrategy = createConnectionKeepAliveStrategy();
        }
        return this.keepAliveStrategy;
    }

    public synchronized void setKeepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy2) {
        this.keepAliveStrategy = keepAliveStrategy2;
    }

    public final synchronized HttpRequestRetryHandler getHttpRequestRetryHandler() {
        if (this.retryHandler == null) {
            this.retryHandler = createHttpRequestRetryHandler();
        }
        return this.retryHandler;
    }

    public synchronized void setHttpRequestRetryHandler(HttpRequestRetryHandler retryHandler2) {
        this.retryHandler = retryHandler2;
    }

    public final synchronized RedirectHandler getRedirectHandler() {
        if (this.redirectHandler == null) {
            this.redirectHandler = createRedirectHandler();
        }
        return this.redirectHandler;
    }

    public synchronized void setRedirectHandler(RedirectHandler redirectHandler2) {
        this.redirectHandler = redirectHandler2;
    }

    public final synchronized AuthenticationHandler getTargetAuthenticationHandler() {
        if (this.targetAuthHandler == null) {
            this.targetAuthHandler = createTargetAuthenticationHandler();
        }
        return this.targetAuthHandler;
    }

    public synchronized void setTargetAuthenticationHandler(AuthenticationHandler targetAuthHandler2) {
        this.targetAuthHandler = targetAuthHandler2;
    }

    public final synchronized AuthenticationHandler getProxyAuthenticationHandler() {
        if (this.proxyAuthHandler == null) {
            this.proxyAuthHandler = createProxyAuthenticationHandler();
        }
        return this.proxyAuthHandler;
    }

    public synchronized void setProxyAuthenticationHandler(AuthenticationHandler proxyAuthHandler2) {
        this.proxyAuthHandler = proxyAuthHandler2;
    }

    public final synchronized CookieStore getCookieStore() {
        if (this.cookieStore == null) {
            this.cookieStore = createCookieStore();
        }
        return this.cookieStore;
    }

    public synchronized void setCookieStore(CookieStore cookieStore2) {
        this.cookieStore = cookieStore2;
    }

    public final synchronized CredentialsProvider getCredentialsProvider() {
        if (this.credsProvider == null) {
            this.credsProvider = createCredentialsProvider();
        }
        return this.credsProvider;
    }

    public synchronized void setCredentialsProvider(CredentialsProvider credsProvider2) {
        this.credsProvider = credsProvider2;
    }

    public final synchronized HttpRoutePlanner getRoutePlanner() {
        if (this.routePlanner == null) {
            this.routePlanner = createHttpRoutePlanner();
        }
        return this.routePlanner;
    }

    public synchronized void setRoutePlanner(HttpRoutePlanner routePlanner2) {
        this.routePlanner = routePlanner2;
    }

    public final synchronized UserTokenHandler getUserTokenHandler() {
        if (this.userTokenHandler == null) {
            this.userTokenHandler = createUserTokenHandler();
        }
        return this.userTokenHandler;
    }

    public synchronized void setUserTokenHandler(UserTokenHandler userTokenHandler2) {
        this.userTokenHandler = userTokenHandler2;
    }

    /* access modifiers changed from: protected */
    public final synchronized BasicHttpProcessor getHttpProcessor() {
        if (this.httpProcessor == null) {
            this.httpProcessor = createHttpProcessor();
        }
        return this.httpProcessor;
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor itcp) {
        getHttpProcessor().addInterceptor(itcp);
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor itcp, int index) {
        getHttpProcessor().addInterceptor(itcp, index);
    }

    public synchronized HttpResponseInterceptor getResponseInterceptor(int index) {
        return getHttpProcessor().getResponseInterceptor(index);
    }

    public synchronized int getResponseInterceptorCount() {
        return getHttpProcessor().getResponseInterceptorCount();
    }

    public synchronized void clearResponseInterceptors() {
        getHttpProcessor().clearResponseInterceptors();
    }

    public synchronized void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> clazz) {
        getHttpProcessor().removeResponseInterceptorByClass(clazz);
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor itcp) {
        getHttpProcessor().addInterceptor(itcp);
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor itcp, int index) {
        getHttpProcessor().addInterceptor(itcp, index);
    }

    public synchronized HttpRequestInterceptor getRequestInterceptor(int index) {
        return getHttpProcessor().getRequestInterceptor(index);
    }

    public synchronized int getRequestInterceptorCount() {
        return getHttpProcessor().getRequestInterceptorCount();
    }

    public synchronized void clearRequestInterceptors() {
        getHttpProcessor().clearRequestInterceptors();
    }

    public synchronized void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
        getHttpProcessor().removeRequestInterceptorByClass(clazz);
    }

    public final HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
        return execute(request, (HttpContext) null);
    }

    public final HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException, ClientProtocolException {
        if (request != null) {
            return execute(determineTarget(request), (HttpRequest) request, context);
        }
        throw new IllegalArgumentException("Request must not be null.");
    }

    private HttpHost determineTarget(HttpUriRequest request) {
        URI requestURI = request.getURI();
        if (requestURI.isAbsolute()) {
            return new HttpHost(requestURI.getHost(), requestURI.getPort(), requestURI.getScheme());
        }
        return null;
    }

    public final HttpResponse execute(HttpHost target, HttpRequest request) throws IOException, ClientProtocolException {
        return execute(target, request, (HttpContext) null);
    }

    /* JADX WARNING: type inference failed for: r17v0, types: [org.apache.http.protocol.HttpContext] */
    /* JADX WARNING: type inference failed for: r2v0, types: [org.apache.http.protocol.HttpContext] */
    /* JADX WARNING: type inference failed for: r19v1 */
    /* JADX WARNING: type inference failed for: r19v2 */
    /* JADX WARNING: type inference failed for: r3v0, types: [org.apache.http.protocol.HttpContext] */
    /* JADX WARNING: type inference failed for: r19v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.apache.http.HttpResponse execute(org.apache.http.HttpHost r23, org.apache.http.HttpRequest r24, org.apache.http.protocol.HttpContext r25) throws java.io.IOException, org.apache.http.client.ClientProtocolException {
        /*
            r22 = this;
            if (r24 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Request must not be null."
            r4.<init>(r5)
            throw r4
        L_0x000a:
            r19 = 0
            r18 = 0
            monitor-enter(r22)
            org.apache.http.protocol.HttpContext r17 = r22.createHttpContext()     // Catch:{ all -> 0x0071 }
            if (r25 != 0) goto L_0x0063
            r19 = r17
        L_0x0017:
            org.apache.http.protocol.HttpRequestExecutor r5 = r22.getRequestExecutor()     // Catch:{ all -> 0x0071 }
            org.apache.http.conn.ClientConnectionManager r6 = r22.getConnectionManager()     // Catch:{ all -> 0x0071 }
            org.apache.http.ConnectionReuseStrategy r7 = r22.getConnectionReuseStrategy()     // Catch:{ all -> 0x0071 }
            org.apache.http.conn.ConnectionKeepAliveStrategy r8 = r22.getConnectionKeepAliveStrategy()     // Catch:{ all -> 0x0071 }
            org.apache.http.conn.routing.HttpRoutePlanner r9 = r22.getRoutePlanner()     // Catch:{ all -> 0x0071 }
            org.apache.http.protocol.BasicHttpProcessor r4 = r22.getHttpProcessor()     // Catch:{ all -> 0x0071 }
            org.apache.http.protocol.BasicHttpProcessor r10 = r4.copy()     // Catch:{ all -> 0x0071 }
            org.apache.http.client.HttpRequestRetryHandler r11 = r22.getHttpRequestRetryHandler()     // Catch:{ all -> 0x0071 }
            org.apache.http.client.RedirectHandler r12 = r22.getRedirectHandler()     // Catch:{ all -> 0x0071 }
            org.apache.http.client.AuthenticationHandler r13 = r22.getTargetAuthenticationHandler()     // Catch:{ all -> 0x0071 }
            org.apache.http.client.AuthenticationHandler r14 = r22.getProxyAuthenticationHandler()     // Catch:{ all -> 0x0071 }
            org.apache.http.client.UserTokenHandler r15 = r22.getUserTokenHandler()     // Catch:{ all -> 0x0071 }
            r0 = r22
            r1 = r24
            org.apache.http.params.HttpParams r16 = r0.determineParams(r1)     // Catch:{ all -> 0x0071 }
            r4 = r22
            org.apache.http.client.RequestDirector r18 = r4.createClientRequestDirector(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x0071 }
            monitor-exit(r22)     // Catch:{ all -> 0x0071 }
            r0 = r18
            r1 = r23
            r2 = r24
            r3 = r19
            org.apache.http.HttpResponse r4 = r0.execute(r1, r2, r3)     // Catch:{ HttpException -> 0x0074 }
            return r4
        L_0x0063:
            org.apache.http.protocol.DefaultedHttpContext r20 = new org.apache.http.protocol.DefaultedHttpContext     // Catch:{ all -> 0x0071 }
            r0 = r20
            r1 = r25
            r2 = r17
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0071 }
            r19 = r20
            goto L_0x0017
        L_0x0071:
            r4 = move-exception
            monitor-exit(r22)     // Catch:{ all -> 0x0071 }
            throw r4
        L_0x0074:
            r21 = move-exception
            org.apache.http.client.ClientProtocolException r4 = new org.apache.http.client.ClientProtocolException
            r0 = r21
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.client.AbstractHttpClient.execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.protocol.HttpContext):org.apache.http.HttpResponse");
    }

    /* access modifiers changed from: protected */
    public RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec2, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor2, HttpRequestRetryHandler retryHandler2, RedirectHandler redirectHandler2, AuthenticationHandler targetAuthHandler2, AuthenticationHandler proxyAuthHandler2, UserTokenHandler stateHandler, HttpParams params) {
        return new DefaultRequestDirector(this.log, requestExec2, conman, reustrat, kastrat, rouplan, httpProcessor2, retryHandler2, redirectHandler2, targetAuthHandler2, proxyAuthHandler2, stateHandler, params);
    }

    /* access modifiers changed from: protected */
    public HttpParams determineParams(HttpRequest req) {
        return new ClientParamsStack(null, getParams(), req.getParams(), null);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return execute(request, responseHandler, (HttpContext) null);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return execute(determineTarget(request), request, responseHandler, context);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return execute(target, request, responseHandler, null);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        if (responseHandler == null) {
            throw new IllegalArgumentException("Response handler must not be null.");
        }
        HttpResponse response = execute(target, request, context);
        try {
            T result = responseHandler.handleResponse(response);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entity.consumeContent();
            }
            return result;
        } catch (Throwable t2) {
            this.log.warn("Error consuming content after an exception.", t2);
        }
        if (t instanceof Error) {
            throw ((Error) t);
        } else if (t instanceof RuntimeException) {
            throw ((RuntimeException) t);
        } else if (t instanceof IOException) {
            throw ((IOException) t);
        } else {
            throw new UndeclaredThrowableException(t);
        }
    }
}
