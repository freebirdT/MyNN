package it.uniroma1.lcl.mynn;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class TestReteNeuralePerceptron {
	
	@Test
	public void testCaricaReteAnd() throws IOException {
		try {
			StringBuilder str = new StringBuilder();
			Files.newBufferedReader(Paths.get(new File(".").getCanonicalPath() + "/reteAnd")).lines().forEach(l -> {
				str.append(l);
				str.append("\n");
			});
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/reteAnd");
			Assert.assertTrue(Math.abs(rete.process(new double[]{0.0,0.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(rete.process(new double[]{1.0,0.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(rete.process(new double[]{0.0,1.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(1.0 - rete.process(new double[]{1.0,1.0})[0]) < 0.01);
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testCaricaToStringAnd() throws IOException {
		try {
			StringBuilder str = new StringBuilder();
			Files.newBufferedReader(Paths.get(new File(".").getCanonicalPath() + "/reteAnd")).lines().forEach(l -> {
				str.append(l);
				str.append("\n");
			});
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/reteAnd");
			
			Assert.assertEquals(str.toString().trim(), rete.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testCaricaNome() throws IOException {
		try {
			StringBuilder str = new StringBuilder();
			Files.newBufferedReader(Paths.get(new File(".").getCanonicalPath() + "/reteAnd")).lines().forEach(l -> {
				str.append(l);
				str.append("\n");
			});
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/reteAnd");

			Assert.assertEquals("ReteAnd", rete.getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testCaricaReteOr() throws IOException {
		try {
			StringBuilder str = new StringBuilder();
			Files.newBufferedReader(Paths.get(new File(".").getCanonicalPath() + "/reteOr")).lines().forEach(l -> {
				str.append(l);
				str.append("\n");
			});
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/reteOr");
			Assert.assertTrue(Math.abs(rete.process(new double[]{0.0,0.0})[0]) < 0.001);
			Assert.assertTrue(Math.abs(1.0 - rete.process(new double[]{1.0,0.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(1.0 -rete.process(new double[]{0.0,1.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(1.0 - rete.process(new double[]{1.0,1.0})[0]) < 0.01);
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testCaricaToStringOr() throws IOException {
		try {
			StringBuilder str = new StringBuilder();
			Files.newBufferedReader(Paths.get(new File(".").getCanonicalPath() + "/reteOr")).lines().forEach(l -> {
				str.append(l);
				str.append("\n");
			});
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/reteOr");
			
			Assert.assertEquals(str.toString().trim(), rete.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testCaricaReteXor() throws IOException {
		try {
			StringBuilder str = new StringBuilder();
			Files.newBufferedReader(Paths.get(new File(".").getCanonicalPath() + "/reteXor")).lines().forEach(l -> {
				str.append(l);
				str.append("\n");
			});
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/reteXor");
			Assert.assertTrue(Math.abs(rete.process(new double[]{0.0,0.0})[0]) < 0.001);
			Assert.assertTrue(Math.abs(1.0 - rete.process(new double[]{1.0,0.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(1.0 - rete.process(new double[]{0.0,1.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(rete.process(new double[]{1.0,1.0})[0]) < 0.01);
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testTrain() throws IOException {
		try {
			
			double[][] inputs = {
					{1.0}, 
					{0.0}, 
					};
			double[][] outputs = {
				{0.0},
				{1.0},
				};
		
				
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/retePercettrone");
		
			rete.train(inputs, outputs);
					 
			Assert.assertTrue(Math.abs(1 - rete.process(new double[]{0.0})[0]) < 0.01);
			Assert.assertTrue(Math.abs(    rete.process(new double[]{1.0})[0]) < 0.01);
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}

