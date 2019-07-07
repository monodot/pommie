package xyz.tomd.pommie.processors;

import org.eclipse.aether.collection.DependencyCollectionException;
import org.eclipse.aether.resolution.DependencyResolutionException;
import org.junit.jupiter.api.Test;
import xyz.tomd.pommie.mavenresolver.ResolverResult;

class BasicPomExplorerTest {

    @Test
    public void testGetDependencyTree() throws DependencyCollectionException {
        BasicPomExplorer pomExplorer = new BasicPomExplorer();

        String result = pomExplorer.getDependencyTree("org.apache.camel:camel-elasticsearch-rest:2.24.0");

        System.out.println(result);
    }

    @Test
    public void testResolve() throws DependencyResolutionException {
        BasicPomExplorer explorer = new BasicPomExplorer();

        ResolverResult result = explorer.resolve("http://localhost:8085/nexus/content/groups/public",
                "org.apache.camel",
                "camel-kubernetes",
                "2.24.0");
    }
}