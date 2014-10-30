package io.pivotal.tooling.plugin.eclipse;

import org.gradle.plugins.ide.internal.tooling.eclipse.DefaultEclipseLinkedResource;
import org.gradle.plugins.ide.internal.tooling.eclipse.DefaultEclipseProject;
import org.gradle.plugins.ide.internal.tooling.eclipse.DefaultEclipseProjectDependency;
import org.gradle.plugins.ide.internal.tooling.eclipse.DefaultEclipseSourceDirectory;
import org.gradle.tooling.internal.gradle.DefaultGradleProject;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class DefaultStsEclipseProject implements Serializable {
    private DefaultStsEclipseExternalDependency externalEquivalent;
    private DefaultGradleProject<?> gradleProject;
    private DefaultEclipseProject hierarchicalEclipseProject;
    private List<DefaultStsEclipseExternalDependency> classpath;
    private DefaultStsEclipseProject parent;
    private List<DefaultStsEclipseProject> children;
    private List<String> plugins;

    public DefaultStsEclipseProject(DefaultEclipseProject hierarchicalEclipseProject,
                                    DefaultGradleProject<?> gradleProject,
                                    List<DefaultStsEclipseExternalDependency> classpath,
                                    List<DefaultStsEclipseProject> children,
                                    DefaultStsEclipseExternalDependency externalEquivalent,
                                    List<String> plugins) {
        this.hierarchicalEclipseProject = hierarchicalEclipseProject;
        this.gradleProject = gradleProject;
        this.classpath = classpath;
        this.children = children;
        this.externalEquivalent = externalEquivalent;
        this.plugins = plugins;
    }

    public List<DefaultStsEclipseExternalDependency> getClasspath() { return classpath; }

    public DefaultGradleProject<?> getGradleProject() { return gradleProject; }

    public DefaultStsEclipseProject getParent() {
        return parent;
    }

    public List<DefaultStsEclipseProject> getChildren() {
        return children;
    }

    public Iterable<? extends DefaultEclipseProjectDependency> getProjectDependencies() {
        return hierarchicalEclipseProject.getProjectDependencies();
    }

    public Iterable<? extends DefaultEclipseSourceDirectory> getSourceDirectories() {
        return hierarchicalEclipseProject.getSourceDirectories();
    }

    public File getProjectDirectory() {
        return hierarchicalEclipseProject.getProjectDirectory();
    }

    public Iterable<? extends DefaultEclipseLinkedResource> getLinkedResources() {
        return hierarchicalEclipseProject.getLinkedResources();
    }

    public void setParent(DefaultStsEclipseProject parent) {
        this.parent = parent;
    }

    public DefaultStsEclipseExternalDependency getExternalEquivalent() { return externalEquivalent; }

    public boolean hasPlugin(Class<?> pluginClass) { return plugins.contains(pluginClass.getName()); }
}