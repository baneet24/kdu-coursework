const os = require('os');

function getSystemInfo() {
  return {
    HostName: os.hostname(),
    OperatingSystem: os.type(),
    Architecture: os.arch(),
    OSRelease: os.release(),
    Uptime: os.uptime(),
    CPUCores: os.cpus().length,
    TotalMemory: os.totalmem(),
    FreeMemory: os.freemem(),
    CurrentWorkingDirectory: process.cwd(),
  };
}
module.exports = getSystemInfo;
