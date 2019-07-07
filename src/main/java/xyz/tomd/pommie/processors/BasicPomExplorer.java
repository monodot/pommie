package xyz.tomd.pommie.processors;

import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.collection.CollectResult;
import org.eclipse.aether.collection.DependencyCollectionException;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.resolution.DependencyResolutionException;
import xyz.tomd.pommie.mavenresolver.Booter;
import xyz.tomd.pommie.mavenresolver.ConsoleDependencyGraphDumper;
import xyz.tomd.pommie.mavenresolver.Resolver;
import xyz.tomd.pommie.mavenresolver.ResolverResult;

import javax.enterprise.context.ApplicationScoped;
import java.io.PrintStream;

@ApplicationScoped
public class BasicPomExplorer {

    public ResolverResult resolve(String remoteRepository, String groupId, String artifactId, String version) throws DependencyResolutionException {
        Resolver resolver = new Resolver( remoteRepository, "target/aether-repo" );
        ResolverResult result = resolver.resolve(groupId, artifactId, version);

        return result;
    }

    public String getDependencyTree(String coords) throws DependencyCollectionException {
        RepositorySystem system = Booter.newRepositorySystem();

        org.eclipse.aether.RepositorySystemSession session = Booter.newRepositorySystemSession( system );

        org.eclipse.aether.artifact.Artifact artifact = new DefaultArtifact(coords);

        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setRoot( new Dependency( artifact, "" ) );
        collectRequest.setRepositories( Booter.newRepositories( system, session ) );

        CollectResult collectResult = system.collectDependencies( session, collectRequest );

//        collectResult.getRoot().accept( new ConsoleDependencyGraphDumper(stream) );
        return collectResult.toString();
    }

}
