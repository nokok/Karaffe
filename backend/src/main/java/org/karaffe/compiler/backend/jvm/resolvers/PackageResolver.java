package org.karaffe.compiler.backend.jvm.resolvers;

import java.util.Objects;
import java.util.Optional;

public class PackageResolver {
    public static Optional<Package> findPackage(String packageName) {
        Objects.requireNonNull(packageName);
        if (packageName.isEmpty()) {
            return Optional.empty();
        }
        Package[] packages = Package.getPackages();
        for (Package pkg : packages) {
            if (pkg.getName().equals(packageName)) {
                return Optional.of(pkg);
            }
        }
        return Optional.empty();
    }
}
