package fes.aragon.controller;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fes.aragon.utilerias.dinamicas.pila.Pila;
import fes.aragon.utilerias.dinamicas.pila.PilaInterface;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class OrdenamientoController implements Initializable {
	double aux = 0;
	int tiempoRetardo=40;
	int numeroDatos=40;
	int mitad = numeroDatos/2;
	
    @FXML
    private BarChart<String, Number> bacGrafica;

    @FXML
    private Button btnBurbuja;

    @FXML
    private Button btnListaNueva;
    
    @FXML
    private Button btnInsercion;
    
    @FXML
    private Button btnSeleccion;
    
    @FXML
    private Button btnMezcla;

    @FXML
    private Button btnQuicksort;



    @FXML
    void metodoBurbuja(ActionEvent event) {
    	this.btnListaNueva.setDisable(true);
    	ObservableList<Data<String,Number>> data = bacGrafica.getData().get(0).getData();
    	Task<Void> animateSortTask = burbujaTask(data);
    	exec.submit(animateSortTask);
    }
    
    @FXML
    void metodoInsercion(ActionEvent event) {
    	this.btnListaNueva.setDisable(true);
    	ObservableList<Data<String,Number>> data = bacGrafica.getData().get(0).getData();
    	Task<Void> animateSorTask = insercionTask(data);
    	exec.submit(animateSorTask);
    	
    }
    
    @FXML
    void metodoSeleccion(ActionEvent event) {
    	this.btnListaNueva.setDisable(true);
    	ObservableList<Data<String,Number>> data = bacGrafica.getData().get(0).getData();
    	Task<Void> animateSorTask = seleccionTask(data);
    	exec.submit(animateSorTask);
    }

    @FXML
    void metodoListaNueva(ActionEvent event) {
    	bacGrafica.getData().clear();
    	Series<String, Number> series = new Series<String, Number>();
    	series = generarAleatoriosEnteros(numeroDatos);
    	bacGrafica.getData().add(series);
    }
    
    @FXML
    void metodoMezcla(ActionEvent event) {
    	this.btnListaNueva.setDisable(true);
    	ObservableList<Data<String,Number>> data = bacGrafica.getData().get(0).getData();
    	
    	Task<Void> animateSorTask = seleccionTask(data.subList(0, mitad));
    	Task<Void> animateSorTask2 = insercionTask(data.subList(mitad, numeroDatos));
    	
    	
    	animateSorTask.setOnSucceeded(seleccionEvenet -> {
    		animateSorTask2.setOnSucceeded(insercionEvenet -> {
    			bacGrafica.getData().clear();
    	    	Series<String, Number> series = new Series<String, Number>();
    	    	series = merge(data.subList(0, mitad), data.subList(mitad, numeroDatos));
    	    	bacGrafica.getData().add(series);
    			
            });
        });
    	
    	exec.submit(animateSorTask);
    	exec.submit(animateSorTask2);
    }
    

	private Series<String, Number> merge(List<Data<String, Number>> list, List<Data<String, Number>> list2) {
		Series<String, Number> nueva = new Series<String, Number>();
		Data<String, Number> primero = null;
		int c= 1, c1 = 0, c2 = 0;
		
		while( c1 < list.size() && c2 < list2.size()) {
			if(list.get(c1).getYValue().doubleValue() < list2.get(c2).getYValue().doubleValue()) {
				primero = list.get(c1);
				nueva.getData().add(new Data<String,Number>( String.valueOf(c), primero.getYValue()));
				c1++;
				c++;
			}else {
				primero = list2.get(c2);
				nueva.getData().add(new Data<String,Number>( String.valueOf(c), primero.getYValue()));
				c2++;
				c++;
			}
		}
		
		while( c1 < list.size()) {
			primero = list.get(c1);
			nueva.getData().add(new Data<String,Number>( String.valueOf(c), primero.getYValue()));
			c1++;
			c++;
		}
		while( c2 < list2.size()) {
			primero = list2.get(c2);
			nueva.getData().add(new Data<String,Number>( String.valueOf(c), primero.getYValue()));
			c2++;
			c++;
		}
		
		return nueva;
	}

	@FXML
    void metodoQuicksort(ActionEvent event) {
		this.btnListaNueva.setDisable(true);
		ObservableList<Data<String,Number>> data = bacGrafica.getData().get(0).getData();
    	Task<Void> animateSorTask = quickSortTask(data);
    	exec.submit(animateSorTask);
    }
    
	
	private Task<Void> quickSortTask(List<Data<String, Number>> data) {
	    return new Task<Void>() {
	    	Data<String, Number> primero = null;
        	Data<String, Number> segundo = null;
        	Data<String, Number> tercero = null;
	        @Override
	        protected Void call() throws Exception {
	        	PilaInterface<CDatos> pila = new Pila<CDatos>();
	        	
	        	
	        	CDatos dato = null;
	    		int izq, der;
	    		int inf = 0;
	    		int sup = numeroDatos - 1;
	    		Integer mitad;
	    		pila.insertar(new CDatos(inf, sup));
	    		
	    		do {
	    			try {
	    				dato = (CDatos)pila.extraer();
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    			inf = dato.obtenerInf();
	    			sup = dato.obtenerSup();
	    			do {
	    				izq = inf;
	    				der = sup;
	    				mitad = data.get((izq+der)/2).getYValue().intValue();
	    				
	    				tercero = data.get((izq+der)/2);
	    				Thread.sleep(tiempoRetardo);
						Platform.runLater(() -> {
	    					tercero.getNode().setStyle("-fx-background-color: green ;");
	    				});
	    				do {
	    					while(data.get(izq).getYValue().doubleValue() < mitad && izq < sup) {
		    					izq++;
		    				}
	    					while(mitad < data.get(der).getYValue().intValue() && der > inf) {
	    						der--;
	    					}
	    					if(izq <= der) {
	    						primero = data.get(izq);
	    						segundo = data.get(der);
	    						
	    						Thread.sleep(tiempoRetardo);
	    						Platform.runLater(() -> {
	    	    					primero.getNode().setStyle("-fx-background-color: blue ;");
	    	    					segundo.getNode().setStyle("-fx-background-color: blue ;");
	    	    				});
	    						
	    						Thread.sleep(tiempoRetardo);
	    						CountDownLatch latch = new CountDownLatch(1);
	    						Platform.runLater(() -> {
	    					    	Animation swap = createSwapAnimation(primero, segundo);
	    				
	    					    	swap.setOnFinished(e -> latch.countDown());
	    					    	swap.play();
	    					    	});
	    				    	latch.await();
	    				    	
	    				    	Thread.sleep(tiempoRetardo);
	    						Platform.runLater(() -> {
	    	    					primero.getNode().setStyle("-fx-background-color: red ;");
	    	    					segundo.getNode().setStyle("-fx-background-color: red ;");
	    	    				});
	    						
	    				    	izq++;
	    				    	der--;
	    					}
	    				}while(izq <= der);
	    				if (izq < sup) {
	    		            pila.insertar(new CDatos(izq, sup));
	    		        }
	    				if(inf < der) {
	    					pila.insertar(new CDatos(inf,der));
	    				}
	    				inf = izq;
	    				sup = der;
	    			}while(inf < sup);
	    		}while(!pila.esVacia());
	    		
	    		for (int i = 0; i < numeroDatos - 1; i++) {
					primero = data.get(i);
					Thread.sleep(tiempoRetardo);
					Platform.runLater(() -> {
    					primero.getNode().setStyle("-fx-background-color: green ;");
    				});

				}
	    		btnListaNueva.setDisable(false);
	            return null;
	        }
	    };
	}
	

    private Task<Void> seleccionTask(List<Data<String, Number>> data){
    	return new Task<Void>() {
    		Data<String, Number> primero = null;
			Data<String, Number> segundo = null;
			@Override
			protected Void call() throws Exception {
				int k = 0;
				double menor = 0;
				
				
				for (int i = 0; i < data.size() - 1; i++) {
					Thread.sleep(tiempoRetardo);
					menor = data.get(i).getYValue().doubleValue();
					k = i;
					for (int j = i + 1; j < data.size(); j++) {
						if(data.get(j).getYValue().doubleValue() < menor) {
							menor = data.get(j).getYValue().doubleValue();
							k = j;
						}
						
					}
					
					primero = data.get(k);
					segundo = data.get(i);
				
					Thread.sleep(tiempoRetardo);
					Platform.runLater(() -> {
    					primero.getNode().setStyle("-fx-background-color: blue ;");
    					segundo.getNode().setStyle("-fx-background-color: blue ;");
    				});
					
					Thread.sleep(tiempoRetardo);
					CountDownLatch latch = new CountDownLatch(1);
			    	Platform.runLater(() -> {
				    	Animation swap = createSwapAnimation(segundo, primero);
			
				    	swap.setOnFinished(e -> latch.countDown());
				    	swap.play();
				    	});
			    	latch.await();
					
			    	Thread.sleep(tiempoRetardo);
					Platform.runLater(() -> {
    					
    					primero.getNode().setStyle("-fx-background-color: red ;");
    				});
				}
				
				btnListaNueva.setDisable(false);
				return null;
			}
    		
    	};
    }
    
    private Task<Void> insercionTask(List<Data<String, Number>> data){
    	return new Task<Void>() {
    		Data<String, Number> primero = null;
			Data<String, Number> segundo = null;
			Data<String, Number> tercero = null;
			@Override
			protected Void call() throws Exception {
				double aux = 0;
				int k = 0;
				
				for(int i = 1; i < data.size(); i++) {
					aux = data.get(i).getYValue().doubleValue();
					k = i - 1;
					Thread.sleep(tiempoRetardo);
					while( k >= 0 && aux < data.get(k).getYValue().doubleValue()) {
						primero = data.get(k);
						segundo = data.get(k + 1);
						
						
						Thread.sleep(tiempoRetardo);
						Platform.runLater(() -> {
		    					primero.getNode().setStyle("-fx-background-color: blue ;");
		    					segundo.getNode().setStyle("-fx-background-color: blue ;");
		    				});
						segundo.setYValue(primero.getYValue());
						Thread.sleep(tiempoRetardo);
						Platform.runLater(() -> {
	    					segundo.getNode().setStyle("-fx-background-color: red ;");
	    				});
						
						CountDownLatch latch = new CountDownLatch(1);
				    	Platform.runLater(() -> {
					    	Animation swap = createSwapAnimation(primero, segundo);
				
					    	swap.setOnFinished(e -> latch.countDown());
					    	swap.play();
					    	});
				    	latch.await();
				    	
				    	k--;
				    	}
					Thread.sleep(tiempoRetardo);
					tercero = data.get(k + 1);
			    	tercero.setYValue(aux);
			    	
			    	
			    	Platform.runLater(() -> {
				    	tercero.getNode().setStyle("-fx-background-color:green ;");
				    	});

			    	}
				
				btnListaNueva.setDisable(false);
				return null;
					}
			};
		}
 
    private Task<Void> burbujaTask(List<Data<String, Number>> data) {

	    	return new Task<Void>() {
	    		Data<String, Number> primero;
	    		Data<String, Number> segundo;
	    	@Override
	    	protected Void call() throws Exception {
	    		
		    	for (int i = data.size() - 1; i >= 0; i--) {
		    		for (int j = 0; j < i; j++) {
			    				primero = data.get(j);
			    				segundo = data.get(j + 1);
			    				Platform.runLater(() -> {
			    					primero.getNode().setStyle("-fx-background-color: red ;");
			    					segundo.getNode().setStyle("-fx-background-color: blue ;");
			    				});
				    	Thread.sleep(tiempoRetardo);
				    	if (primero.getYValue().doubleValue() >= segundo.getYValue().doubleValue()) {
				
					    	CountDownLatch latch = new CountDownLatch(1);
					    	Platform.runLater(() -> {
						    	Animation swap = createSwapAnimation(primero, segundo);
					
						    	swap.setOnFinished(e -> latch.countDown());
						    	swap.play();
						    	});
					    	latch.await();
					    	}
				    	Thread.sleep(tiempoRetardo);
				    	Platform.runLater(() -> {
					    	primero.getNode().setStyle("-fx-background-color:blue ;");
					
					    	segundo.getNode().setStyle("-fx-background-color:red ;");
					    	});
			    	}
		    	}
		    	for (int i = 0; i < numeroDatos - 1; i++) {
					primero = data.get(i);
					Thread.sleep(tiempoRetardo);
					Platform.runLater(() -> {
    					primero.getNode().setStyle("-fx-background-color: green ;");
    				});

				}
		    	btnListaNueva.setDisable(false);
		    	return null;
		    	}
	    	};
    	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		bacGrafica.setAnimated(false);
		Series<String, Number> series = new Series<String, Number>();

		series = generarAleatoriosEnteros(numeroDatos);

		bacGrafica.getData().add(series);
	}
	private Series<String, Number> generarAleatoriosEnteros(int n) {
		Series<String, Number> series = new Series<>();
		Random rnd = new Random();
		for (int i = 1; i <= n; i++) {
		series.getData().add(new Data<>(String.valueOf(i), rnd.nextInt(90) + 10));
		}
		return series;
		}

	private <T> Animation createSwapAnimation(Data<?, T> primero, Data<?, T> segundo) {
		double primeroX = primero.getNode().getParent().localToScene(primero.getNode().getBoundsInParent()).getMinX();

		double segundoX = primero.getNode().getParent().localToScene(segundo.getNode().getBoundsInParent()).getMinX();

		double primeroStartTranslate = primero.getNode().getTranslateX();
		double segundoStartTranslate = segundo.getNode().getTranslateX();
		TranslateTransition primeroTranslate = new TranslateTransition(Duration.millis(tiempoRetardo),

		primero.getNode());

		primeroTranslate.setByX(segundoX - primeroX);
		TranslateTransition sgundoTranslate = new TranslateTransition(Duration.millis(tiempoRetardo),

		segundo.getNode());

		sgundoTranslate.setByX(primeroX - segundoX);
		ParallelTransition translate = new ParallelTransition(primeroTranslate, sgundoTranslate);
		translate.statusProperty().addListener((obs, oldStatus, newStatus) -> {
			if (oldStatus == Animation.Status.RUNNING) {
				T temp = primero.getYValue();
				primero.setYValue(segundo.getYValue());
				segundo.setYValue(temp);
				primero.getNode().setTranslateX(primeroStartTranslate);
				segundo.getNode().setTranslateX(segundoStartTranslate);
				}
			});
		return translate;
		}
		private ExecutorService exec = Executors.newCachedThreadPool(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
		
		
}
