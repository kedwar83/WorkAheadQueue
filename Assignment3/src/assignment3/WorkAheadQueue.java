/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import ADTs.WorkAheadQueueADT; 
import DataStructures.LinearNode;
import Exceptions.EmptyCollectionException;
import Exceptions.InvalidArgumentException;
import java.util.ArrayList;

/**
 *
 * @author kedwa
 * @param <T>
 */
public class WorkAheadQueue<T> implements WorkAheadQueueADT<T> {
   
   LinearNode<T> front;
   LinearNode<T> back;
   ArrayList<LinearNode<T>> frontThreeNodes;
   ArrayList<T> frontThreeElements;
   int numNodes;

   public WorkAheadQueue() {
       front = back = null;
       numNodes = 0; 
       frontThreeElements = new ArrayList<T>();
       frontThreeNodes = new ArrayList<LinearNode<T>>();
   }
   
   @Override
   public T dequeue(int x)throws EmptyCollectionException, 
           InvalidArgumentException {
       if (numNodes == 0) {
           throw new EmptyCollectionException("a"); 
       } else if (x < 0 || x > 2 || x > (numNodes - 1)) {
           throw new InvalidArgumentException("a"); 
       } else {
           T result = frontThreeElements.get(x);
       
       if (x == 0) {
           if (numNodes == 1) {
               front = back = null;
               
               frontThreeNodes.remove(0);
               frontThreeElements.remove(0);
           
           } else {
           front = front.getNext();
           
           frontThreeNodes.remove(0);
           frontThreeElements.remove(0);
           
           }
       
       } else if (x == 1) {
               
               if (numNodes >= 3) {
                   front.setNext(frontThreeNodes.get(2));
               } else {
                   front.setNext(null);
                   back = front;
               }
               
               frontThreeNodes.remove(1);
               frontThreeElements.remove(1);
       } else {
            frontThreeNodes.get(1).setNext(
                frontThreeNodes.get(2).getNext());

           frontThreeElements.remove(2);
           frontThreeNodes.remove(2);
       }
     
       if (numNodes > 3) {
          frontThreeNodes.add(
                frontThreeNodes.get(frontThreeNodes.size() -1).getNext()
          );
          frontThreeElements.add(
                frontThreeNodes.get(frontThreeNodes.size() -1).getElement()
          );
                   }
       
       numNodes = numNodes - 1; 
       return result;
       }
   }
   
   @Override
   public void enqueue(T element) {
       if (numNodes < 3) {
           if (numNodes == 0) {
           front = new LinearNode<T>(element);
           back = front; 
           
           frontThreeElements.add(element);
           frontThreeNodes.add(front);
           
           numNodes = numNodes + 1;
       } else {
           LinearNode<T> temp = new LinearNode<T>(element);
          
               back.setNext(temp);
               back = temp;
               
               frontThreeElements.add(element);
               frontThreeNodes.add(temp);
           
               numNodes = numNodes + 1;
       }
    } else {
        LinearNode<T> temp = new LinearNode<T>(element);

        back.setNext(temp);
        back = temp; 
       
        numNodes = numNodes + 1;
   }
}
   @Override
   public T first(int x) throws EmptyCollectionException,
   InvalidArgumentException {
       if (numNodes == 0) {
           throw new EmptyCollectionException(); 
     } else if (x < 0 || x > 2 || x > (numNodes)) {
           throw new InvalidArgumentException("a"); 
       } else {
           T result = frontThreeElements.get(x);
           
           return result;
        }
   }
   
    @Override
    public ArrayList<LinearNode<T>> firstThreeNodes() throws EmptyCollectionException{
       return frontThreeNodes;
   }
   
       public ArrayList<T> firstThreeElements() throws EmptyCollectionException{
      if (isEmpty()) {
          throw new EmptyCollectionException("a");  
          }
          
          frontThreeElements.clear(); 
          LinearNode<T> curr = front; 
          
          for (int i = 0; i < 3 && i < size(); i++) {
             frontThreeElements.add(i, curr.getElement());
             curr = curr.getNext(); 
        }
       return frontThreeElements;
   }
    
      public T first() throws EmptyCollectionException{
       if (numNodes == 0) {
           throw new EmptyCollectionException();
       } else {
       return frontThreeElements.get(0);
   }
      }

      
      
   @Override
   public T dequeue() throws EmptyCollectionException {
       try {
           return dequeue(0); 
       } catch (EmptyCollectionException e) {
           throw e;
       } catch (InvalidArgumentException e) {
           return null; 
       }
   }
   
  
   
   /**
    * returns an ArrayList of the first three elements in the queue. 
    * 
    * 
    */
  

   @Override
   public boolean isEmpty() {
       return numNodes == 0;
   }
  
   @Override
   public int size() {
       return numNodes;
   }
   
    @Override
    public String toString() {
       StringBuffer sb = new StringBuffer();
       if (front != null) {
           LinearNode<T> temp = front;
           while (temp.getNext() != null) {
               sb.append(temp.getElement().toString()).append("->");
               temp = temp.getNext();
           }
           sb.append(temp.getElement());
       }
       return sb.toString();
   }
}
