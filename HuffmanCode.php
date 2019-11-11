<?php
/**
 * Created by PhpStorm.
 * User: dev
 * Date: 11.11.19
 * Time: 19:44
 */


function characterCount($inputString)
{
    $result = array();

    foreach (count_chars($inputString,1) as $char => $count){
        $result[chr($char)] = $count;
    }
    return $result;
}


function encodeMessage($inputString)
{
    $queue = new SplPriorityQueue();
    $priority = 1;
    foreach (characterCount($inputString) as $char => $count){
            $queue->insert(new Leaf($char,$count), $priority);
            $priority += 1;
    }
    $summa = 0;
    while ($queue->count() > 1){
        $firstNode = $queue->extract();
        $secondNode = $queue->extract();
        $node = new InternalNode($firstNode, $secondNode);
        $queue->insert($node,$priority-1);
        $summa += $node->amount;
    }
    print $summa;
}




class Node {

    public $amount;

    public function __construct($amount)
    {
        $this->amount = $amount;
    }


    function comparator($nodeAmount){
        return $this->amount <=> $nodeAmount->amount  ;
    }
}



class InternalNode extends Node {

    public $leftNode;
    public $rightNode;

    public function __construct($leftNode, $rightNode)
    {
        $this->leftNode = $leftNode;
        $this->rightNode = $rightNode;
        parent::__construct($leftNode->amount + $rightNode->amount);
    }
}


class Leaf extends Node {

    public $amount;

    public function __construct($char, $amount)
    {
        parent::__construct($amount);
        $this->$char = $char;

    }

}


encodeMessage('abacabad');
