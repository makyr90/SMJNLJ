# SMJNLJ
### Implement two join algorithms, SMJ (Sort Merge Join) and NLJ (Nested Loops Join) in java
Program accepts the following command line arguments:

* **-f1** <file1 path>: full path to file1
* **-a1** <file1_join_attribute>: the column to use as join attribute from file1 (counting from 0)
* **-f2** <file2 path>: same as above for file2 fff
* **-a2** <file2_join_attribute>: same as above for file2
* **-j** <join_algorithm_to_use>: SMJ or NLJ
* **-m** <available_memory_size>: we use as memory metric the number of records*
* **-t** <temporary_dir_path>: a directory to use for reading/writing temporary files
* **-o** <output_file_path>: the file to store the result of the join

For example, in order to join two relations stored in files “R.csv” and “S.csv” on the first column of R and the second column of S, using Sort-Merge Join, having available memory = 100 records and saving the result to file “results.csv” one should execute the following command:

**java –jar joinalgs.jar –f1 R.csv –a1 0 –f2 S.csv –a2 1 –j SMJ –m 100 –t tmp –o results.csv**