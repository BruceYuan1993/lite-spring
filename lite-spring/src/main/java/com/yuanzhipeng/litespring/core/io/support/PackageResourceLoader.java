package com.yuanzhipeng.litespring.core.io.support;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.yuanzhipeng.litespring.core.io.FileSystemResource;
import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.util.Assert;
import com.yuanzhipeng.litespring.util.ClassUtils;

public class PackageResourceLoader {
    private final ClassLoader classLoader;

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "classLoader must not be null.");
        this.classLoader = classLoader;
    }

    public Resource[] getResources(String basePackage) {
        // TODO Auto-generated method stub
        Assert.notNull(basePackage, "Base package must not be null.");
        String resourecePath = ClassUtils.convertClassNameToResourcePath(basePackage);
        URL url = classLoader.getResource(resourecePath);
        File rootDir = new File(url.getFile());

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];
        int i=0;
        for (File file : matchingFiles) {
            result[i++]=new FileSystemResource(file);
        }
        return result;
    }

    private Set<File> retrieveMatchingFiles(File rootDir) {
        // TODO Auto-generated method stub
        if (!rootDir.exists()) {
            // Silently skip non-existing directories.
            return Collections.emptySet();
        }
        if (!rootDir.isDirectory()) {
            // Complain louder if it exists but is no directory.
            return Collections.emptySet();
        }
        if (!rootDir.canRead()) {
            return Collections.emptySet();
        }
        /*String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
        if (!pattern.startsWith("/")) {
            fullPattern += "/";
        }
        fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
        */
        Set<File> result = new LinkedHashSet<File>(8);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    private void doRetrieveMatchingFiles(File dir, Set<File> result) {

        File[] dirContents = dir.listFiles();
        if (dirContents == null) {
            return;
        }
        for (File content : dirContents) {

            if (content.isDirectory()) {
                if (!content.canRead()) {
                } else {
                    doRetrieveMatchingFiles(content, result);
                }
            } else {
                result.add(content);
            }

        }
    }
}
