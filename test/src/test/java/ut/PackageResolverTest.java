package ut;

import org.junit.Test;
import org.karaffe.compiler.backend.jvm.resolvers.PackageResolver;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class PackageResolverTest {

    @Test
    public void testPackageResolver() {
        Optional<Package> packageOpt = PackageResolver.findPackage("java.lang");
        assertEquals("java.lang", packageOpt.get().getName());
    }
}
