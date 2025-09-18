package com.dali.fuaicode.ai.core.builder;

import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.core.util.RuntimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class VueProjectBuilder {

    public void buildProjectAsync(String projectPath) {
        Thread.ofVirtual().name("vue-builder-" + System.currentTimeMillis()).start(() ->{
            try {
                buildProject(projectPath);
            } catch (Exception e) {
                log.error("构建项目失败", e);
            }
        });
    }




    /**
     * 构建 Vue 项目
     * @param projectPath 项目路径
     * @return 是否构建成功
     */

    public boolean buildProject(String projectPath) {
        File projectDir = new File(projectPath);
        if (!projectDir.exists() || !projectDir.isDirectory()) {
            log.error("项目目录不存在: {}", projectPath);
            return false;
        }
        // 检查 package.json 文件
        File packageJson = new File(projectDir, "package.json");
        if (!packageJson.exists()) {
            log.error("项目目录下不存在 package.json 文件");
            return false;
        }
        log.info("开始构建项目:{}" , projectPath);

        if (!executeNpmInstall(projectDir)) {
            log.error("npm install 失败");
            return false;
        }
        if (!executeNpmBuild(projectDir)) {
            log.error("npm run build 构建失败");
            return false;
        }
        // 检查 dist 目录
        File distDir = new File(projectDir, "dist");
        if (!distDir.exists() || !distDir.isDirectory()) {
            log.error("项目构建失败，未找到 dist 目录");
            return false;
        }
        log.info("项目构建成功");
        return true;
    }


    private boolean executeCommand(File workingDir, String command, int timeoutSeconds) {
        try {
            log.info("在目录 {} 中执行命令：{}", workingDir.getAbsolutePath(), command);
            Process process = RuntimeUtil.exec(
                    null,
                    workingDir,
                    command.split("//s+")
            );
            // 等待命令执行完成
            boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
            if (finished) {
                int exitCode = process.exitValue();
                if (exitCode == 0) {
                    log.info("命令执行成功");
                    return true;
                } else {
                    log.error("命令执行失败，退出码为 {}", exitCode);
                    return false;
                }
            } else {
                log.error("命令执行超时");
                return false;
            }
        } catch (Exception e) {
            log.error("命令执行 {} 失败 ,异常信息：{}",command, e.getMessage());
            return false;
        }
    }


    /**
     * 执行 npm install
     * @param projectDir 项目目录
     * @return 是否执行成功
     */
    private boolean executeNpmInstall(File projectDir) {
        log.info("开始执行 npm install");
        String command = String.format("%s install", buildCommand("npm"));
        return executeCommand(projectDir, command, 300);
    }

    /**
     * 执行 npm build
     * @param projectDir 项目目录
     * @return 是否执行成功
     */

    private boolean executeNpmBuild(File projectDir) {
        log.info("开始执行 npm build");
        String command = String.format("%s run build", buildCommand("npm"));
        return executeCommand(projectDir, command, 300);
    }

    /**
     * 判断当前操作系统是否为 Windows
     * @return true 表示当前操作系统为 Windows
     */

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }



    private String buildCommand(String baseCommand) {
        if (isWindows()) {
            return baseCommand  + ".cmd";
        }
        return baseCommand;
    }




}
