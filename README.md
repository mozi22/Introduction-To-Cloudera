# Introduction-To-Cloudera

##How TO Setup Cloudera using VirtualBox

I used **linux** as a working environment to setup cloudera on my system.


### Step 1: Download Cloudera

Download the [VirtualBox image](http://www.cloudera.com/downloads/quickstart_vms/5-8.html) for cloudera.

### Step 2: Download VirtualBox

Download virtualbox from their [website](https://www.virtualbox.org/wiki/Downloads).

Setup a new virtual box image using the Cloudera image downloaded and apply the following settings.

- Give it `4 processors`.
- 6 GB RAM recommended ( otherwise 4 GB RAM atleast )
- Linux version = `Other Linux` ( 64|32 bits based on your system )
- Go to your Image settings > General > Advanced and do the following settings. 
 - set `Shared Clipboard` to  `Bidirectional` to enable copying from and into `VM`.
 - set `Drag'n Drop` to  `Host To Guest` to enable drag and drop from `host machine` inside `image`.

#### Step 2.1: Setup

- Go to File > Preferences > Networks
- Setup a host-only adapter there.
- Make sure you've two adapters. `Host-only Adapter` and `NAT`.

### Step 3: Start VM Image.

### List of Commands

Once you enter the VM Image. You can open the terminal with a small black icon on the top

Following are the commands I needed to work with `hdfs` and `local file system` of cloudera.

1. `-ifconfig`: This will show you 2 ip addresses out of which one will be the Host-only network ip you mentioned during step 2.1.
 1. You can use that ip to copy files from your system to clouder ( also by drag and drop as we did the setting for it in previous step. )
2. `scp wordsbyte.jar cloudera@192.168.56.101:/home/cloudera`: Copy files from host machine to cloudera. Where `192.168.56.101` is the ip address i mentioned during my host-only network configuration of virtual box. You can always drag and drop also.
3. `hadoop fs -put wordcount.jar /user/cloudera/`: Copy file `wordcount.jar` from `cloudera local file system` to `hdfs`where `/user/cloudera/` is the target path.
4. `hadoop fs -ls /user/cloudera/`: see list of files in a directory
5. `hadoop fs -rm -r -f /user/cloudera/myfolder`: To remove a folder
6. `hadoop fs -cat /user/cloudera/myfolder/part-r-00000`: View the data in file generated after the mapreduce job finished. The file is named `part-r-00000` by the job.
7. `ssh cloudera@192.168.56.101`: connect to cloudera machine from your host machine terminal. ( The password is `cloudera` ).
8. `hadoop jar myjar.jar myapplication.package.mainclass arg[0] arg[1] ....` : This is to run the program using the exported jar from your Java program.
9. `hadoop fs -copyToLocal /user/cloudera/folder/file /~` : Copy file from hdfs to local file system.
10. `mv oldname newname`: To rename a file.

### How to Run this code.

1. Copy the `twain.txt` and `english.stop.txt` file to hadoop file system (hdfs) . Using command 2 and 3.
2. Go to the cloned java program and select `File > Export > Java > Jar` and export the jar file.
3. Copy the jar file to the cloudera local file system using command 2.
4. Run job using command 8 by specifying the jar file and sending two paths as arguments. Suppose our jar is called `wordcount.jar`. The overall command will look like this.
  - `hadoop jar wordcount.jar msp_exercise.exercise.RowCount /user/cloudera/twain* /user/cloudera/wordcount`.
   - Since our main driver class is `RowCount` we directly mentioned the class in the jar file.
   - The first path is to the twain file which should be present in `/user/cloudera`as mentioned in step 1 and the `/user/cloudera/wordcount` is the second argument which we are sending. This will be the output folder where our results will be saved in a file after our job completes.
5. Once the result is saved in your output path file which will be named `part-r-00000`. You can view it using command 6. 
6. You can copy it back from hdfs to `cloudera local file system` using command 9 and rename the strange `part-r-00000` filename using command 10.

**Note: The driver file RowCount has the 3rd task uncommented by default. You can comment any two task to export a jar file using the step 2 and follow steps from there.**

For further help: Follow [these video tutorials](https://www.youtube.com/watch?v=nPRY-qGaAMs&list=PLf0swTFhTI8rnNRnVz6n-f1d3ZCDtgqRq).
