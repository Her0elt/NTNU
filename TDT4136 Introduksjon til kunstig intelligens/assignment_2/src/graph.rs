use priority_queue::DoublePriorityQueue;

#[derive(Debug, Hash, Clone)]
pub struct Node {
    pub id: usize,
    pub is_wall: bool,
    pub cost: i64,
    pub dist_to_end: i64,
    pub x: i64,
    pub y: i64,
    pub total: i64,
    pub best_parent_cord: (usize, usize),
    pub seen: bool,
}

impl PartialEq for Node {
    fn eq(&self, other: &Self) -> bool {
        self.id == other.id
    }
}

impl Eq for Node {}

pub struct Graph {
    pub board: Vec<Vec<Node>>,
}

impl Graph {
    fn calc_dist_end(&self, node: &Node, end: &Node) -> i64 {
        return ((end.x - node.x) as i64).abs() + ((end.y - node.y) as i64).abs();
    }

    pub fn astar(&mut self, start: &mut Node, end: &mut Node) -> Vec<i64> {
        let mut prio_que = DoublePriorityQueue::new();
        start.dist_to_end = self.calc_dist_end(&start, &end);
        let start_weight = 0;
        start.total = start_weight;
        prio_que.push(start.clone(), start_weight);
        self.board[start.x as usize][start.y as usize].seen = true;
        while !prio_que.is_empty() {
            let (node, _) = prio_que.pop_min().unwrap();
            if node.id == end.id {
                break;
            }
            let potential_neighbours: [(i64, i64); 4] = [
                (node.x, node.y + 1),
                (node.x, node.y - 1),
                (node.x + 1, node.y),
                (node.x - 1, node.y),
            ];
            for (x, y) in potential_neighbours {
                let xp = x as usize;
                let yp = y as usize;
                let kid = self.board[xp][yp].clone();
                if !kid.seen && !kid.is_wall {
                    let cost = kid.cost;
                    let mut total = kid.total;
                    let mut dist_to_end = kid.dist_to_end;
                    if dist_to_end == -1 {
                        dist_to_end = self.calc_dist_end(&kid, end);
                    }
                    if total > node.total + cost {
                        self.board[xp][yp].best_parent_cord = (node.x as usize, node.y as usize);
                        total = node.total + cost;
                    }
                    self.board[xp][yp].dist_to_end = dist_to_end;
                    self.board[xp][yp].total = total;
                    self.board[xp][yp].seen = true;
                    let weight = total + dist_to_end;
                    let new_kid = self.board[xp][yp].clone();
                    prio_que.push(new_kid, weight);
                }
            }
        }

        let mut node = self.board[end.x as usize][end.y as usize].clone();
        let mut nodes: Vec<i64> = vec![];
        nodes.push(node.id as i64);
        while node.id != start.id {
            let x = node.best_parent_cord.0;
            let y = node.best_parent_cord.1;
            node = self.board[x][y].clone();
            nodes.push(node.id as i64);
        }
        nodes
    }
}
