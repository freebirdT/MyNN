package it.uniroma1.lcl.mynn;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class TestReteNeuraleQualsiasiFunzione {
	
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
			
			// provare diversi learning rate. Con 0.2 e la struttura che ho dato funziona bene 
			double[][] inputs = {
					{0.479, 0.371},
					{0.128, 0.406},
					{0.157, 0.157},
					{0.275, 0.218},
					{0.835, 0.780},
					{0.855, 0.752},
					{0.785, 0.641},
					{0.876, 0.845},
					{0.812, 0.544},
					{0.332, 0.155},
					{0.258, 0.134},
					{0.233, 0.235},
					{0.297, 0.317},
					{0.883, 0.897},
					{0.858, 0.588},
					{0.238, 0.208},
					{0.268, 0.397},
					{0.326, 0.269},
					{0.759, 0.985},
					{0.795, 0.636},
					{0.849, 0.834},
					{0.752, 0.732},
					{0.794, 0.847},
					{0.782, 0.738},
					{0.898, 0.824},
					{0.828, 0.943},
					{0.170, 0.163},
					{0.373, 0.437},
					{0.195, 0.109},
					{0.175, 0.349},
					{0.166, 0.306},
					{0.319, 0.240},
					{0.379, 0.223},
					{0.323, 0.305},
					{0.141, 0.248},
					{0.734, 0.540},
					{0.678, 0.740},
					{0.749, 0.671},
					{0.660, 0.850},
					{0.804, 0.784}
					};
			double[][] outputs = {
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{1,0},
					{1,0},
					{0,1},
					{0,1},
					{0,1},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{0,1},
					{1,0},
					{1,0},
					{1,0},
					{1,0},
					{1,0}
				};
				
			IReteNeurale rete = IReteNeurale.carica(new File(".").getCanonicalPath() + "/reteSum");
		
			rete.train(inputs, outputs);
					 
			double[] out1 = rete.process(new double[]{0.25,0.25});
			
			Assert.assertTrue(Math.abs(    out1[0]) < 0.1);
			Assert.assertTrue(Math.abs(1 - out1[1]) < 0.1);
			
			double[] out2 = rete.process(new double[]{0.75,0.75});
						
			Assert.assertTrue(Math.abs(1 - out2[0]) < 0.1);
			Assert.assertTrue(Math.abs(    out2[1]) < 0.1);
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}

