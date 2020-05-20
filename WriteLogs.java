
FileWriter fstream = new FileWriter("D:\\JmeterTestData\\CINTemp.csv",true);

BufferedWriter out = new BufferedWriter(fstream);

out.write(vars.get("BRANCH") + "," +vars.get("ACCOUNT_NO") + "," + vars.get("returnCode"));

out.write(System.getProperty("line.separator"));

out.close();

fstream.close();
