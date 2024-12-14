import {ActionIcon, Group, Table, Text} from '@mantine/core';
import {Link} from "react-router-dom";
import {IconPlus} from "@tabler/icons-react";

const data = [
    {
        name: 'Math',
    },
    {
        name: 'Programming',
    },
    {
        name: 'Physics',
    },
];

export default function ListDisciplinesComponent() {
    const rows = data.map((item) => (
        <Table.Tr key={item.name}>
            <Table.Td ta={"left"}>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.name}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td ta={"right"}>
                <Link to={"/create-test"}>
                    <ActionIcon variant="subtle" color="gray">
                        <IconPlus>
                            Создать Дисциплину
                        </IconPlus>
                    </ActionIcon>
                </Link>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Thead>
                    <Table.Tr>
                        <Table.Th ta={"left"}>Название Дисциплины</Table.Th>
                    </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}