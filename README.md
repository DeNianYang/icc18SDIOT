# SD-IOT

## Run Configuration
1. Import as a Maven project.
2. Increase application [heap size] in run configuration.
```
-Xmx4096m
```

## JGraphT
* JGraphT version used: 1.01
* Some methods used in this project have been marked as `Deprecated` by the latest [JGraphT API].
* e.g. `GmlImporter` and `VertexProvider` in `Topo.java`.

## Useful Links
- [Github JGraphT Wiki](https://github.com/jgrapht/jgrapht/wiki)
- [JGraphT Javadocs](http://jgrapht.org/javadoc/)
- [Github Guava Wiki](https://github.com/google/guava/wiki)
- [Topology Zoo](http://www.topology-zoo.org/dataset.html)
- [物件相等性](https://openhome.cc/Gossip/JavaEssence/ObjectEquality.html)
- [Generate equals() and hashCode() with Eclipse](http://www.baeldung.com/java-eclipse-equals-and-hashcode)
- [A Guide to Naming Variables](https://a-nickels-worth.blogspot.tw/2016/04/a-guide-to-naming-variables.html)

## Stack Overflow
- [How to find an object in an ArrayList by property](https://stackoverflow.com/questions/17526608/how-to-find-an-object-in-an-arraylist-by-property)
- [Iterate through a HashMap](https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap)
- [How to refer to custom Vertexes to add an Edge, using JGraphT](https://stackoverflow.com/questions/23837638/how-to-refer-to-custom-vertexes-to-add-an-edge-using-jgrapht)
- [Measure execution time for a Java method](https://stackoverflow.com/questions/3382954/measure-execution-time-for-a-java-method)

[heap size]: https://stackoverflow.com/questions/15313393/how-to-increase-application-heap-size-in-eclipse
[JGraphT API]: http://jgrapht.org/javadoc/deprecated-list.html

## Citation
If you find this work or code is helpful in your research, please cite our work:
```
@INPROCEEDINGS{8422236,
  author={Wang, Chih-Hang and Kuo, Jian-Jhih and Yang, De-Nian and Chen, Wen-Tsuen},
  booktitle={2018 IEEE International Conference on Communications (ICC)}, 
  title={Green Software-Defined Internet of Things for Big Data Processing in Mobile Edge Networks}, 
  year={2018},
  pages={1-7},
  doi={10.1109/ICC.2018.8422236}}
```
