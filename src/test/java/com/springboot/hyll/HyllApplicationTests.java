package com.springboot.hyll;

import com.springboot.hyll.sys.entity.Tree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class HyllApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1(){
		List<Tree> treeList = new ArrayList<Tree>();
		Tree t = new Tree(1l);
		treeList.add(t);
		t = new Tree(2l);
		treeList.add(t);
		t = new Tree(3l);
		treeList.add(t);

		List<Tree> treeList2 = new ArrayList<Tree>();
		t = new Tree(3l);
		treeList2.add(t);
		t = new Tree(4l);
		treeList2.add(t);
		treeList.stream().forEach(tt->{
			treeList2.stream().forEach(ttt->{
				if(ttt.getId()==tt.getId()){
					tt.setName("数值一样");
				}
			});
		});

		treeList.stream().forEach(tt->{
			System.out.println(tt.getId()+"--"+tt.getName());
		});

	}

}
