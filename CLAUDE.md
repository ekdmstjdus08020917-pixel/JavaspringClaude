# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```powershell
# Compile
mvn clean compile

# Package as WAR (output: target/ROOT.war)
mvn clean package

# Skip tests during packaging
mvn clean package -DskipTests
```

Deploy `target/ROOT.war` to a Tomcat server. The WAR is named `ROOT` so it serves from the context root (`/`).

The "tests" in `src/test/java` are plain `main()` programs — not JUnit. Run them as Java applications from Eclipse or via `mvn exec:java`. They require a live MySQL connection to `192.168.110.113:3306/kopodb`.

## Architecture

This is a Spring MVC 5.3.6 web application (Java 11, Servlet 4.0) that serves as a learning project demonstrating two styles of Spring DI.

### Package structure

**`com.kopo.hkcode.pojo`** — XML-based DI (legacy/commented out)  
`Hello` depends on the `Printer` interface; `StringPrinter` and `ConsolePrinter` implement it. Originally wired via `beans.xml`, now disabled in favor of the annotation package.

**`com.kopo.hkcode.annot`** — Annotation-based DI  
Same `Hello`/`Printer` structure, but wired with `@Component`, `@Autowired`, `@Qualifier`, `@Value`. Active via `beans_annt.xml` component scan.

**`com.kopo.hkcode.product`** — Database layer  
`ProductService` uses raw JDBC (`DataSource`) to query the `kopo_channel_seasonality_new` MySQL table. `ProductController` exposes `POST /getMaxQty` returning JSON via `@ResponseBody`.

**`com.kopo.hkcode` (root)**  
`HelloController` handles `GET /hello` and `GET /helloTest`, returning JSP views from `/WEB-INF/views/`.

### Spring configuration

| File | Purpose |
|------|---------|
| `web.xml` | Bootstraps `DispatcherServlet` with `servlet-context.xml` |
| `servlet-context.xml` | MVC config: component scan, view resolver (prefix `/WEB-INF/views/`, suffix `.jsp`), CORS (`/**` open), imports the three bean files |
| `beans.xml` | Original POJO bean definitions — all commented out |
| `beans_annt.xml` | Enables annotation component scan for `com.kopo.hkcode.annot` |
| `beans_product.xml` | Wires `DataSource` from `db.properties` + `ProductService` constructor args |

### Database

Connection properties live in `src/main/resources/db.properties` (copied to classpath). The `beans_product.xml` loads it via `<context:property-placeholder>` and builds a `DriverManagerDataSource`. No connection pool — each call opens and closes a connection.
