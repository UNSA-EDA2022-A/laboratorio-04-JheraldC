package com.example.project;

public class SinglyLinkedList<T extends Comparable<T>> {
	private Node<T> first; // Primero nodo de la lista
	private int size; // Tamano de la lista

	// Constructor (crea lista vacia)
	SinglyLinkedList() {
		first = null;
		size = 0;
	}

	// Retorna el tamano de la lista
	public int size() {
		return size;
	}

	// Devuelve true si la lista esta vazia o false caso contrario
	public boolean isEmpty() {
		return (size == 0);
	}

	// Adiciona v al inicio de la lista
	public void addFirst(T v) {
		Node<T> newNode = new Node<T>(v, first);
		first = newNode;
		size++;
	}

	// Adiciona v al final de la lista
	public void addLast(T v) {
		Node<T> newNode = new Node<T>(v, null);
		if (isEmpty()) {
			first = newNode;
		} else {
			Node<T> cur = first;
			while (cur.getNext() != null)
				cur = cur.getNext();
			cur.setNext(newNode);
		}
		size++;
	}

	// Retorna el primer valor de la lista (o null si la lista esta vacia)
	public T getFirst() {
		if (isEmpty())
			return null;
		return first.getValue();
	}

	// Retorna el ultimo valor de la lista (o null si la lista esta vazia)
	public T getLast() {
		if (isEmpty())
			return null;
		Node<T> cur = first;
		while (cur.getNext() != null)
			cur = cur.getNext();
		return cur.getValue();
	}

	// Elimina el primer elemento de la lista (si esta vacia no hara nada)
	public void removeFirst() {
		if (isEmpty())
			return;
		first = first.getNext();
		size--;
	}

	// Elimina el ultimo elemento de la lista (si esta vacia no hara nada)
	public void removeLast() {
		if (isEmpty())
			return;
		if (size == 1) {
			first = null;
		} else {
			// Ciclo con for y uso de size para mostrar alternativa al while
			Node<T> cur = first;
			for (int i = 0; i < size - 2; i++)
				cur = cur.getNext();
			cur.setNext(cur.getNext().getNext());
		}
		size--;
	}

	// Convierte la lista para um String
	public String toString() {
		String str = "{";
		Node<T> cur = first;
		while (cur != null) {
			str += cur.getValue();
			cur = cur.getNext();
			if (cur != null)
				str += ",";
		}
		str += "}";
		return str;
	}

	// NUEVOS METODOS

	// Elimina el primer nodo que su valor sea x
	public void remove(T x) {
		if (!isEmpty()) {
			if (first.getValue().equals(x)) {
				first = first.getNext();
				return;
			}
			Node<T> aux = first;
			while (aux.getNext() != null) {
				if (aux.getNext().getValue().equals(x)) {
					aux.setNext(aux.getNext().getNext());
					return;
				}
				aux = aux.getNext();
			}
		}
		size--;
	}

	// Elimina aquellos nodos de la lista que esten duplicados
	public void deleteDuplicates() {

		Node<T> actual = first; // Declaro actual para seguir el recorrido de la lista
		Node<T> temp; // Declaro temp para compararlo con actual
		while (actual.getNext() != null) { // Mientras que el nodo siguiente de actual no sea null continue
			temp = actual.getNext(); // temp inicializa con el nodo siguiente a actual
			while (temp != null) { // Mientras que temp sea diferente de null continue
				if (actual.getValue().compareTo(temp.getValue()) == 0) { // Sabiendo si son iguales o no
					remove(temp.getValue()); // Si son iguales que remueva el primer nodo que contenga a la variable
					break; // Y que siga con la siguiente actual
				}
				temp = temp.getNext(); // Si no se cumple que vea al siguiente temp y lo compare
			}
			actual = actual.getNext(); // Al finalizar el while, que siga con el siguiente actual para que los temp lo
										// comparen
		}

	}

	// Inserta un nuevo nodo en una posicion especifica de la lista
	public void insertNth(T data, int position) {
		Node<T> aux = first; // Declaro aux para ayudarme a recorrer la lista
		if (position == 0) { // Si la posicion es 0, entonces
			addFirst(data); // Añado la data al primer nodo
		} else if (position <= size) { // Si la posicion es menor e igual a size, o sea si esta en rango
			while (aux.getNext() != null && position != 1) { // Mientras que el nodo siguiente a aux no sea null y la
																// posicion no sea 1
				aux = aux.getNext(); // aux será el siguiente nodo
				position--; // Y restaré a la posicion 1 por cada iteracion, de esa manera encontrare la
							// posicion que deseo
			}
			aux.setNext(new Node<T>(data, aux.getNext())); // Si llegó, significa que encontro la posicion y la colocará
															// junto la data y su nodo siguiente
		} else {
			System.out.println("Fuera de rango."); // Si no se cumple ninguno, significa que esta fuera de rango
		}
	}

	// Elimina el nodo de una posicion especifica de la lista
	public void deleteNth(int position) {
		Node<T> ant = first; // Declaro ant para que sea el anterior del nodo objetivo
		Node<T> sig = first; // Declaro sig para que sea el siguiente del nodo objetivo
		if (position == 1) { // Si la posicion es 1 entonces
			first.setNext(first.getNext().getNext()); // El primer nodo se enlazará con el tercer nodo
		} else if (position == 0) { // Si la posicion es 0 entonces
			removeFirst(); // Se removera el primer nodo
		} else if (position < size) { // Si la posicion es menor que el size
			while (sig.getNext() != null && position != 1) { // Mientras que el nodo sigueinte a sig no sea null y la
																// posicion no sea 1
				ant = sig; // ant sera el nodo anterior al nuevo sig
				sig = sig.getNext(); // sig sera el nodo siguiente
			}
			if (sig.getNext() == null) { // Terminado el bucle. Si el nodo siguiente a sig es null entonces
				ant.setNext(null); // el nodo siguiente a ant será null
			} else { // Si no se cumple, es diferente de null
				ant.setNext(sig.getNext()); // ant será el nodo siguiente de sig
			}
		} else {
			System.out.println("Fuera de rango.");// En caso no se cumpla ninguno, significa que esta fuera de rango
		}

	}

	public static void main(final String[] args) {

		// testExercicio1();
		// testExercicio2();
		testExercicio3();

	}

	public static void testExercicio1() {

		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

		list.addLast(47);
		list.addLast(89);
		list.addLast(56);
		list.addLast(89);
		list.addLast(56);

		System.out.println(list);

		list.deleteDuplicates();

		System.out.println(list);
	}

	public static void testExercicio2() {

		SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

		list.addLast('a');
		list.addLast('b');
		list.addLast('d');

		System.out.println(list);

		list.insertNth('c', 2);

		System.out.println(list);
	}

	public static void testExercicio3() {

		SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

		list.addLast('a');
		list.addLast('b');
		list.addLast('d');

		System.out.println(list);

		list.deleteNth(2);

		System.out.println(list);
	}

}
