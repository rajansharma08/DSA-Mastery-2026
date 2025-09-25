class MinStack {
    Stack<Pair> st;

    private class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        int newMin;
        if(st.isEmpty()){
            newMin = val;
        }else{
            newMin = Math.min(val, st.peek().second);
        }

        st.push(new Pair(val, newMin));
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek().first;
    }

    public int getMin() {
        return st.peek().second;
    }
}
