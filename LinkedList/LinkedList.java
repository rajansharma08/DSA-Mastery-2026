public class LinkedList {
    Node head;

    public void append(int data) {
        Node newNode = new Node(data);

        // Case:1 if LL is null
        if (head == null) {
            head = newNode;
            return;
        }

        // Case:2 if LL is not null then traverse till end
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        last.next = newNode;
    }

    public void prepend(int data){
        Node newNode = new Node(data);

        newNode.next = head;

        head = newNode;
    }
}
